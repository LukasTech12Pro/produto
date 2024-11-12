function cadastroLojas() {
    var nome = $("#nome").val();
    var endereco = $("#endereco").val();
    var latitude = $("#latitude").val();
    var longitude = $("#longitude").val();

    axios.post('http://localhost:8080/lojas', {
        "nome" : nome,
        "endereco" : endereco,
        "latitude" : latitude,
        "longitude" : longitude
    })
    .then(function (response) {
        document.getElementById("successMessage").textContent = "Loja cadastrado com sucesso!";

        //Abre o modal
        var myModal = new bootstrap.Modal(document.getElementById('successModal'));
        myModal.show();

        document.getElementById('successModal').addEventListener('hidden.bs.model', function () {
            document.location = "cadLoja.html";
        });
    })
    .catch(function (error) {
        console.log("Erro ao cadastrar loja:", error);
        document.getElementById("errorMessage").textContent = "Error ao cadastrar loja. Tente novamente";

        //Abre o modal
        var myModal = new bootstrap.Modal(document.getElementById('errorModal'));
        myModal.show();
    })
}