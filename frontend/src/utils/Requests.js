import {API_BASE_URL} from './Constants';


const request = (options) => {
    const headers = new Headers({
        'Content-Type': 'application/json',
    })


    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    return fetch(options.url, options)
        .then(response =>
            response.json().then(json => {
                if (!response.ok) {
                    return Promise.reject(json);
                }
                return json;
            })
        );
};

export function getSongsByArtist(value) {
    return request({
        url: API_BASE_URL + "/songs/artists/" + value,
        method: 'GET',
    });
}


export function getSongsByWord(value) {
    return request({
        url: API_BASE_URL + "/songs/contains/" + value,
        method: 'GET',
    });
}

export function getSongsByText(value) {
    return request({
        url: API_BASE_URL + "/songs/builder/contains/" + value,
        method: 'GET',
    });
}