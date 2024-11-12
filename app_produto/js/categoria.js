function carregarDados() {
    axios.get('http://localhost:8080/categoria')
    .then(function (response) {
        const categorias = response.data;
        const categoriaSelect = document.getElementById('categoria');

        categoriaSelect.innerHTML = '';

        categorias.forEach(function(categoria) {
            const option = document.createElement('option');
            option.value = categoria.id;
            option.textContent = categoria.nome;
            categoriaSelect.appendChild(option);
        });
    })
    .catch(function (error) {
        console.log('Erro ao carregar categorias:', error);
    });
}