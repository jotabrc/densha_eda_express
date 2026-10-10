#!/bin/bash

# JaCoCo Coverage Tool
# Script para execução e análise de cobertura de testes com JaCoCo

set -e

PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$PROJECT_ROOT"

echo "========================================="
echo "JaCoCo Coverage Tool"
echo "========================================="
echo ""

# Função para exibir ajuda
show_help() {
    echo "Uso: ./jacoco.sh [opção]"
    echo ""
    echo "Opções disponíveis:"
    echo "  test       Executar testes com cobertura de testes"
    echo "  report     Gerar relatório de cobertura (requer testes executados)"
    echo "  clean      Limpar artefatos de build e relatórios"
    echo "  verify     Executar testes e verificar cobertura (se configurado)"
    echo "  open       Abrir relatório HTML no navegador padrão"
    echo "  help       Exibir esta mensagem de ajuda"
    echo ""
    echo "Exemplos:"
    echo "  ./jacoco.sh test"
    echo "  ./jacoco.sh report"
    echo "  ./jacoco.sh open"
}

# Função para executar testes com cobertura
run_test() {
    echo "Executando testes com cobertura de testes..."
    mvn clean test
    echo ""
    echo "Testes concluídos. Relatório gerado em: target/site/jacoco/index.html"
}

# Função para gerar relatório
generate_report() {
    echo "Gerando relatório de cobertura..."
    mvn jacoco:report
    echo ""
    echo "Relatório gerado em: target/site/jacoco/index.html"
}

# Função para limpar artefatos
clean_build() {
    echo "Limpando artefatos de build e relatórios..."
    mvn clean
    echo ""
    echo "Limpeza concluída."
}

# Função para verificar cobertura
verify_coverage() {
    echo "Executando verificação de cobertura..."
    mvn clean verify
    echo ""
    echo "Verificação concluída."
}

# Função para abrir relatório no navegador
open_report() {
    local report_path="$PROJECT_ROOT/target/site/jacoco/index.html"
    
    if [ -f "$report_path" ]; then
        echo "Abrindo relatório no navegador..."
        
        if command -v xdg-open &> /dev/null; then
            xdg-open "$report_path"
        elif command -v open &> /dev/null; then
            open "$report_path"
        elif command -v firefox &> /dev/null; then
            firefox "$report_path"
        elif command -v google-chrome &> /dev/null; then
            google-chrome "$report_path"
        else
            echo "Não foi possível detectar um navegador. Abra manualmente:"
            echo "  $report_path"
        fi
    else
        echo "Relatório não encontrado em: $report_path"
        echo "Execute primeiro: ./jacoco.sh test"
    fi
}

# Processar argumentos
case "${1:-}" in
    test)
        run_test
        ;;
    report)
        generate_report
        ;;
    clean)
        clean_build
        ;;
    verify)
        verify_coverage
        ;;
    open)
        open_report
        ;;
    help|--help|-h)
        show_help
        ;;
    "")
        echo "Nenhuma opção fornecida."
        echo ""
        show_help
        exit 1
        ;;
    *)
        echo "Opção desconhecida: $1"
        echo ""
        show_help
        exit 1
        ;;
esac
