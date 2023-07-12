// Preenche os dados do endereço
function preencherEndereco(dados) {
    document.getElementById('logradouro').value = dados.logradouro;
    document.getElementById('bairro').value = dados.bairro;
    document.getElementById('cidade').value = dados.localidade;
    document.getElementById('estado').value = dados.uf;
}

// Exibi mensagem de erro
function exibirErro() {
    document.getElementById('cep-erro').classList.remove('d-none');
}

// Limpa os campos de endereço
function limparEndereco() {
    document.getElementById('logradouro').value = '';
    document.getElementById('bairro').value = '';
    document.getElementById('cidade').value = '';
    document.getElementById('estado').value = '';
}

// Manipulador do evento de entrada do CEP
function handleCepInput() {
    const cepInput = document.getElementById('cep');
    const cepValue = cepInput.value.replace(/\D/g, '');

    if (cepValue.length === 8) {
        // Limpa a mensagem de erro
        document.getElementById('cep-erro').classList.add('d-none');

        // Faz a solicitação para obter os dados do endereço
        fetch(`https://viacep.com.br/ws/${cepValue}/json/`)
            .then(response => response.json())
            .then(data => {
                if (data.erro) {
                    // Se o CEP não foi encontrado, ele exibi mensagem de erro
                    limparEndereco();
                    exibirErro();
                } else {
                    // Preenche os campos de endereço
                    preencherEndereco(data);
                }
            })
            .catch(error => {
                console.log(error);
                limparEndereco();
                exibirErro();
            });
    } else {
        // Se o CEP não for válido, ele limpa os campos de endereço
        limparEndereco();
    }
}

// Esse código está adicionando um ouvinte de evento (addEventListener) para capturar as mudanças de valor no campo de CEP e executar a função handleCepInput quando essas mudanças ocorrerem.
document.getElementById('cep').addEventListener('input', handleCepInput);
