const sendByApi = (method, url, params, server, success_callback, failure_callback) => {
    let content_type = 'application/json'
    let send_url = url
    let options = {
        method: method,
        headers: {
            "Content-Type": content_type
        }
    }

    if(method === 'get') {
        send_url += "?" + new URLSearchParams(params)
    } else {
        options['body'] = JSON.stringify(params)
    }

    fetch(send_url, options)
        .then(response => {
            response.json()
                // API 호출 성공 (서버 호출 성공)
                .then(json => {
                    // 요청 결과 성공 
                    if(response.status >= 200 && response.status < 300) {  // 200 ~ 299
                        if(success_callback) {
                            alert("날씨를 성공적으로 불러왔어요");
                            success_callback(server, json)
                        }
                    }
                    // 요청 결과 오류 
                    else {
                        if (failure_callback) {
                            alert("날씨를 불러오지 못했어요");
                            failure_callback(json)
                        }else {
                            alert(JSON.stringify(json))
                        }
                    }
                })
                // API 호출 오류 (서버 호출 오류)
                .catch(error => {
                    alert("API 호출 오류");
                    alert(JSON.stringify(error))
                })
        })

}
// today = {
//     "day_txt":day_txt,
//     "time_txt":time_txt,
//     "temp":temp,
//     "temp_min":temp_min,
//     "temp_max":temp_max,
//     "humidity":humidity,
//     "wind_speed":wind_speed,
//     "feels_like":feels_like,
//     "rainfall":pop
// }

const dtFormat = (json_data) => {
    document.getElementById('day').innerHTML = json_data['day_txt'];
    document.getElementById('time').innerHTML = json_data['time_txt'];
    document.getElementById('temp').innerHTML = json_data['temp'];
    document.getElementById('feels_like').innerHTML = json_data['feels_like'];
    // document.getElementById('temp_min').innerHTML = json_data['temp_min'];
    // document.getElementById('temp_max').innerHTML = json_data['temp_max'];
    document.getElementById('humidity').innerHTML = json_data['humidity'];
    document.getElementById('wind_speed').innerHTML = json_data['wind_speed'];
    document.getElementById('rainfall').innerHTML = json_data['rainfall'];
}
const successFnc = (server, json) => {
    alert(JSON.stringify(json));
    dtFormat(json);
}
const failureFnc = (json) => {
    alert(JSON.stringify(json));
}

async function mainApi(server, url, params) {
    try {
        await sendByApi('get', url, params, server, successFnc, failureFnc);
    } catch(e) {
        console.log(e);
    }
}

// const api_info = {
//     "java": {
//         "host":"http://localhost:8080",
//         "tag": document.getElementById("java")
//     },
//     "python": {
//         "host":"http://localhost:8000",
//         "tag": document.getElementById("python")
//     }
// }

const showWth = () => {
    let url = "http://localhost:8000/api/wewh";
    let area_value = document.getElementById("nameEn").innerText;
    mainApi("python", url, {"city_en_code" : area_value});
}
showWth();
