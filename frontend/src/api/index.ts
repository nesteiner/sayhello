import Axios from "axios";

const instance = Axios.create({
    // baseURL: "http://localhost:8082"
    baseURL: "http://localhost/api"
})

const LOCAL_TOKEN_KEY = "token";

async function login(username: string, password: string) {
    // let response = await instance.post("/authenticate", {
    //     username,
    //     password
    // });
    //
    // let jwttoken = response.data.jwttoken;

    let jwttoken = await instance.post("/authenticate", {
        username,
        password
    })
        .then(response => response.data.jwttoken)
        .catch(error => {
            let errorMessage = error.response.data.message;
            throw errorMessage;
        });

    let authorization = `Bearer ${jwttoken}`;
    localStorage.setItem(LOCAL_TOKEN_KEY, authorization);

}

function logout() {
    localStorage.removeItem(LOCAL_TOKEN_KEY);
}

async function findMatchedMessages() {
    let jwttoken = localStorage.getItem(LOCAL_TOKEN_KEY) || "no token";
    let response = await instance.get("/message", {
        headers: {
            "Authorization": jwttoken
        }
    }).catch(error => {
        throw error.response.data.message;
    });

    return response.data.data

}

async function findAllMessages() {
    let jwttoken = localStorage.getItem(LOCAL_TOKEN_KEY) || "no token";
    let response = await instance.get("/message/all", {
        headers: {
            "Authorization": jwttoken
        }
    });
    return response.data.data
}

async function sendMessage(text: string) {
    let jwttoken = localStorage.getItem(LOCAL_TOKEN_KEY) || "no token";
    let response = await instance.post("/message", {
        body: text
    }, {
        headers: {
            "Authorization": jwttoken
        }
    });

    return response.data.data
}
export {
    LOCAL_TOKEN_KEY,
    logout,
    login,
    findMatchedMessages,
    findAllMessages,
    sendMessage
}