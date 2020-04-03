// For a pie chart
new Chart(document.getElementById("myPieChartProjects"), {
    type: 'doughnut',
    data: {
        labels: ['NOT STARTED', 'COMPLETED', 'IN PROGRESS'],
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ['gray','green','orange'],
            borderColor: ['whitesmoke'],
            data: [5, 10, 5]
        }]
    },

    // Configuration options go here
    options: {}
})

new Chart(document.getElementById("myPieChartEmployees"), {
    type: 'doughnut',
    data: {
        labels: ['SENIOR', 'JUNIOR', 'INTERMEDIARY'],
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ['blue','red','gray'],
            borderColor: ['whitesmoke'],
            data: [5, 10, 5]
        }]
    },

    // Configuration options go here
    options: {}
})
