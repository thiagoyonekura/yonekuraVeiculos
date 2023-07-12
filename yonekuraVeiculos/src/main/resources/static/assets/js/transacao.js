function confirmarExclusaoTransacao(event) {
    if (!confirm("Deseja realmente excluir a transação?")) {
        event.preventDefault();
    }
}

function confirmarAlteracao() {
    var confirmacao = confirm("Tem certeza que deseja salvar as alterações?");
    return confirmacao;
}

function clearFilter(){
    window.location = '/listarTransacao';
}