const getAllProducts = () => {
    fetch(`./api/products`)
        .then(result => {
           return result.json()
        }).then(data => {
            let html = '';
            data.forEach(d => html += `<tr><td>${d.name}</td><td>${d.price}</td><td>
                                        <button class="btn-primary" onclick="showDetails(${d.id})">Details</button>
                                        </td></tr>`)
            document.getElementById("product-table").innerHTML = html;
        })
}

function showDetails(productId) {
    fetch(`./api/products/`+productId)
        .then(response => response.json())
        .then(data => {
            let span = document.getElementById("description");
            span.innerText = data.description;
            let container = document.getElementById("descriptionContainer");
            container.hidden = false;
        })
}

function login(mail, pwd) {
    let data = {
        mail: mail,
        password: pwd
    };

    fetch(`,/api/login`, {
        method: 'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(data)
    }).then(response => {
        if (response.status == 200)
            return response.json()
        else
            throw new Error(response.statusText)
    }).then( data => document.getElementById("logged_in").innerHTML = data.name
    ).catch(
        error => alert(error)
    )
}