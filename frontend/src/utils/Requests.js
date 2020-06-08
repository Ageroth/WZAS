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

export function getSongsByText(value, flag) {
    return request({
        url: API_BASE_URL + "/songs/builder/contains/sentence?text=" + value,
        method: 'POST',
        body: JSON.stringify(flag)
    });
}