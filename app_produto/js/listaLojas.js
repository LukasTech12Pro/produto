function getAllLojas() {
    axios.get('http://localhost:8080/lojas')
    .then(function (response) {
        console.log(response);

        var jsonData = response.data;
        var tableBody = $("table tbody");

        jsonData.forEach(loja => {
            var markup = "<tr>" + 
                        "<td>" + loja.id + "</td>" +
                        "<td>" + loja.nome + "</td>" +
                        "<td>" + loja.endereco + "</td>" +
                        "<td>" + loja.latitude + "</td>" +
                        "<td>" + loja.longitude + "</td>" +
                        "</tr>";

            tableBody.append(markup);
        });
    })
    .catch (function (error) {
        console.log(error);
    });
}