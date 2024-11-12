

//RECUPERA A INFORMACAO DE TODOS OS 
//PRODUTOS CADASTRADOS NO SISTEMA
function getAllProdutos() {

    axios.get('http://localhost:8080/produto')
    .then(function (response) {
        console.log(response);

        var jsonData = response.data;
        var tableBody = $("table tbody");

        jsonData.forEach(produto => {
            
            var markup = "<tr>" +
                        "<td>" + produto.id + "</td>" +
                        "<td>" + produto.nome + "</td>" +
                        "<td> R$ " + produto.preco + "</td>" +
                        "<td>" + produto.dataCadastro + "</td>" +
                        "<td>" + (produto.categoria ? produto.categoria.nome : "") + "</td>" +
                        "<td>" + (produto.fornecedor ? produto.fornecedor.nome : "") + "</td>" +
                        "<td>" + "<button type='button' class='btn btn-danger' onclick='deletaProduto(" + produto.id + ")'>Deletar</button>" + "</td>" +
                        "</tr>";

            tableBody.append(markup);            
        });
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });

} //fim function

function deletaProduto(idProduto) {
    axios.delete('http://localhost:8080/produto/' + idProduto)
    .then (function (response) {
        //Exibe o modal de sucesso
        document.getElementById("successMessage").textContent = "Produto deletado com sucesso!";

        //Abre o modal
        var myModal = new bootstrap.Modal(document.getElementById('successModal'));
        myModal.show();

        //Configura o botão para redirecionar para a lista de produtos
        document.getElementById('successModal').addEventListener('hidden.bs.modal', function () {
            document.location = "listaProduto.html";
        });
    })
    .catch(function (error) {
        //Exibe o modal de erro
        console.log("Erro ao deletar produto:", error);
        document.getElementById("errorMessage").textContent = "Erro ao deletar o produto. Tente novamente.";

        //abre o modal
        var myModal = new bootstrap.Modal(document.getElementById('errorModal'));
        myModal.show();
    });
}


function getAllCategorias() {
    
}