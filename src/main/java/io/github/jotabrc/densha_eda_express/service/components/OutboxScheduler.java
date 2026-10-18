package io.github.jotabrc.densha_eda_express.service.components;

import io.github.jotabrc.densha_eda_express.domain.Outbox;
import io.github.jotabrc.densha_eda_express.domain.OutboxStatus;
import io.github.jotabrc.densha_eda_express.service.OutboxPublisherService;
import io.github.jotabrc.densha_eda_express.service.OutboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class OutboxScheduler {

    @Value("${densha.outbox.find-by-status.page-size:100}")
    private int pageSize;

    @Value("${densha.outbox.find-by-status.max-collection-size:1000}")
    private int maxCollectionSize;

    private final OutboxService outboxService;
    private final OutboxPublisherService outboxPublisherService;

    @Transactional
    @Scheduled(fixedRateString = "${densha.outbox.fixed-rate:10000}",
            initialDelayString = "${densha.outbox.initial-delay:60000}")
    public void execute() {
        Pageable pageable = PageRequest.of(0, pageSize);
        int collectionSize = 0;
        Page<Outbox> page;
        do {
            page = outboxService.findByStatus(OutboxStatus.PENDING, pageable);
            page.getContent().forEach(outboxPublisherService::publish);

            collectionSize += page.getContent().size();
            if (page.hasNext()) {
                pageable = page.nextPageable();
            }
        } while (page.hasNext() && collectionSize < maxCollectionSize);
    }

    @Transactional
    @Scheduled(fixedRateString = "${densha.outbox.clean-up.fixed-rate:60000}",
            initialDelayString = "${densha.outbox.clean-up.initial-delay:120000}")
    public void executeCleanUp() {
        Pageable pageable = PageRequest.of(0, pageSize);
        int collectionSize = 0;
        Page<Outbox> page;
        do {
            page = outboxService.findByStatus(OutboxStatus.SENT, pageable);
            outboxService.deleteAll(page.getContent());

            collectionSize += page.getContent().size();
            if (page.hasNext()) {
                pageable = page.nextPageable();
            }
        } while (page.hasNext() && collectionSize < maxCollectionSize);
    }
}
