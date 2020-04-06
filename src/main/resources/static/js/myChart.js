//Change cryptic data into decode and nice Json
const niceDataChartPro = decodeHtml(dataChartPro);
const niceDataChartEmp = decodeHtml(dataChartEmp);
//Convert String into JSON object (Array)
const JsonChartArrayPro = JSON.parse(niceDataChartPro);
const JsonChartArrayEmp = JSON.parse(niceDataChartEmp);

const proValuesArr = [];
const proLabelsArr = [];
const empValuesArr = [];
const empLabelsArr = [];

for (let i = 0; i < JsonChartArrayPro.length; i++) {
    proValuesArr[i] = JsonChartArrayPro[i].value;
    proLabelsArr[i] = JsonChartArrayPro[i].label;
}

for (let i = 0; i < JsonChartArrayEmp.length; i++) {
    empValuesArr[i] = JsonChartArrayEmp[i].value;
    empLabelsArr[i] = JsonChartArrayEmp[i].label;
}

// For a pie chart
new Chart(document.getElementById("myPieChartProjects"), {
    type: 'doughnut',
    data: {
        labels: proLabelsArr,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ['gray','green','orange'],
            borderColor: ['whitesmoke'],
            data: proValuesArr
        }]
    },

    // Configuration options go here
    options: {
        display: true,
        text: "Project statuses"
    }
});

new Chart(document.getElementById("myPieChartEmployees"), {
    type: 'doughnut',
    data: {
        labels: empLabelsArr,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ['blue','red','gray'],
            borderColor: ['whitesmoke'],
            data: empValuesArr
        }]
    },

    // Configuration options go here
    options: {
        display: true,
        text: "Employees statuses"
    }
});

//[{"value":1,"label":"COMPLETED"},{"value":2,"label":"INPROGRESS"},{"value":1,"label":"NOTSTARTED"}]
function decodeHtml(html)
{
    const txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}
