$( "button.btn-secondary" ).on( "click", function( event ) {
    document.location = '../index.html';
});

function carregarSelect() {
    axios.get('http://localhost:8080/categoria')
    .then(function (response) {
        var jsonData = response.data;
        var select = $("#categoria");

        jsonData.forEach(categoria => {
            optionText = categoria.nome;
            optionValue = categoria.id;
            let optionHTML = `
            <option value="${optionValue}"> 
                ${optionText} 
            </option>`;
            select.append(optionHTML);
        });
    })
    .catch (function (error) {
        console.log(error);
    });

    axios.get('http://localhost:8080/fornecedor')
    .then(function (response) {
        var jsonData = response.data;
        var select = $("#fornecedor");

        jsonData.forEach(fornecedor => {
            optionText = fornecedor.nome;
            optionValue = fornecedor.id;
            let optionHTML = `<option value = "${optionValue}"> ${optionText} </option>`;
            select.append(optionHTML);
        });
    })
    .catch (function (error) {
        console.log(error);
    });
}

function cadastrarProdutos() {
    var nome = $("#nome").val();
    var preco = $("#preco").val();
    var dataCadastro = $("#data").val();
    var categoria = $("#categoria").val();
    var fornecedor = $("#fornecedor").val();

    axios.post('http://localhost:8080/produto', {
        "nome" : nome,
        "preco" : preco,
        "dataCadastro" : dataCadastro,
        "categoria" : {
            "id" : categoria
        },
        "fornecedor" : {
            "id" : fornecedor
        }
    })
    .then(function (response) {
        document.getElementById("successMessage").textContent = "Produto cadastrado com sucesso!";

        //Abre o modal
        var myModal = new bootstrap.Modal(document.getElementById('successModal'));
        myModal.show();

        //Configura o botão para redirecionar para a lista de produtos
        document.getElementById('successModal').addEventListener('hidden.bs.modal', function () {
            document.location = "cadProduto.html";
        });
    })
    .catch(function (error) {
        console.log("Erro ao cadastrar produto:", error);
        document.getElementById("errorMessage").textContent = "Error ao cadastrar produto. Tente novamente.";

        //abre o modal
        var myModal = new bootstrap.Modal(document.getElementById('errorModal'));
        myModal.show();
    })
}

carregarSelect();