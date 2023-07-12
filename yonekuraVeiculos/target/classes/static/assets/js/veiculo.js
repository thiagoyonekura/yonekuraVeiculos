function confirmarExclusaoVeiculo(event) {
    if (!confirm("Deseja realmente excluir o veículo?")) {
        event.preventDefault();
    }
}

function confirmarAlteracao() {
    var confirmacao = confirm("Tem certeza que deseja salvar as alterações?");
    return confirmacao;
}

function clearFilter(){
    window.location = '/listarVeiculo';
}

function exibirMensagemErro(mensagem) {
    $.notify({
        message: mensagem
    },{
        type: 'danger'
    });
}

$.ajax({
    error: function(xhr, status, error) {
        exibirMensagemErro(xhr.responseText);
    }
});