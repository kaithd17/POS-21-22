_url = "./customer"
let _activeCustomer;
const getCustomer = (customerId) => {
    fetch(_url + "/" + customerId).then(response => {
        console.log(response.status)
        showInfo("GET", response.status);
        return response.json()
    })
        .then(data => {
        for (let key in data) {
            let tf = document.getElementById(key);
            if (tf != null) {
                tf.value = data[key];
            }
        }
    })
        .catch(err => {
        console.error(err)
    })
}

const showInfo = (status, method) => {
    document.getElementById("info").innerHTML = "HTTP-Method: " + method + "<br/> HTTP-Status: " + status;
}

const postCustomer = () => {
    let customer = {
        "customerId" : customerId.value,
        "firstname" : firstname.value,
        "lastname" : lastname.value,
        "gender" : gender.value,
        "active" : active.value,
        "email" : email.value,
        "since" : since.value,
    }
    fetch(_url, {
        method: "post",
        headers: {
            'Content-Type' : 'application/json',
        },
        body: JSON.stringify(customer)
    })
        .then(response => {
            showInfo("POST", response.status);
            _activeCustomer = response.headers.get("Location") //<-- url vom neuen customer
        })
        .catch(err => {
            console.error(err)
        })
}

const putCustomer = () => {
    let customer = {
        "customerId" : customerId.value,
        "firstname" : firstname.value,
        "lastname" : lastname.value,
        "gender" : gender.value,
        "active" : active.value,
        "email" : email.value,
        "since" : since.value,
    }
    fetch(_url, {
        method: "put",
        headers: {
            'Content-Type' : 'application/json',
        },
        body: JSON.stringify(customer)
    })
        .then(response => {
            showInfo("PUT", response.status)
        })
        .catch(err => {
            console.error(err)
        })
}

const patchCustomer = (customerId) => {
    let customer = {
        "customerId" : customerId.value,
        "firstname" : firstname.value,
        "lastname" : lastname.value,
        "gender" : gender.value,
        "active" : active.value,
        "email" : email.value,
        "since" : since.value,
    }
    fetch(_url + "/" + customerId, {
        method: "patch",
        headers: {
            'Content-Type' : 'application/json',
        },
        body: JSON.stringify(customer)
    })
        .then(response => {
            showInfo("PATCH", response.status)
        })
        .catch(err => {
            console.error(err)
        })

}

const deleteCustomer = (customerId) => {

}