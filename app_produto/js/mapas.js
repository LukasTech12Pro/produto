let map;
async function initMap(params) {

    map = L.map('map').setView([-15.7801, -47.9292], 13);

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    axios.get('http://localhost:8080/lojas')
    .then(response => {
        var jsonData = response.data;
        jsonData.forEach(loja => {
            var marker = L.marker([loja.latitude, loja.longitude]).addTo(map);
            marker.bindPopup("<b>" + loja.nome + "</b><br>" + loja.endereco);
        });
    })
    .catch(error => {
        console.log(error);
    });
}

initMap();