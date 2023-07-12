function confirmarExclusaoPessoa(event) {
    if (!confirm("Deseja realmente excluir a pessoa?")) {
        event.preventDefault();
    }
}

function confirmarAlteracao() {
    var confirmacao = confirm("Tem certeza que deseja salvar as alterações?");
    return confirmacao;
}

function clearFilter(){
    window.location = '/listarPessoa';
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
