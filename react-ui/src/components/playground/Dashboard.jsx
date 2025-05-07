import { useEffect, useState } from "react";
import { Chart } from 'primereact/chart';
import axios from "axios";
function ChartDashboard() {
    const [chartData, setChartData] = useState({});
    const [chartOptions, setChartOptions] = useState({});
    const [labelData, setLabels] = useState([])
    const [numData, setNumData] = useState([])

    useEffect(() => {
        const getBarChartData = async () => {
            let resp = await axios.get('http://localhost:8080/api/product/bar-chart');
            setLabels(resp.data.labels)
            setNumData(resp.data.numData)
            console.log(resp.data.labels)
            const data = {
                labels: labelData,
                datasets: [
                    {
                        label: 'Product Count',
                        data: numData,
                        backgroundColor: [
                            'rgba(227, 123, 18, 0.84)',
                            'rgba(29, 203, 203, 0.89)',
                            'rgba(18, 153, 243, 1)',
                            'rgba(153, 102, 255, 0.2)'
                        ],
                        borderColor: [
                            'rgb(255, 159, 64)',
                            'rgb(75, 192, 192)',
                            'rgb(54, 162, 235)',
                            'rgb(153, 102, 255)'
                        ],
                        borderWidth: 1
                    }
                ]
            };
            setChartData(data)
        }

        getBarChartData();

    }, [])
    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-lg-12">
                    Navbar
                </div>
            </div>
            <div className="row">
                <div className="col-lg-12">
                    text stats
                </div>
            </div>
            <div className="row">
                <div className="col-md-6">
                    Pie Chart
                    <div className="card">
                        <div className="card-header">

                        </div>
                        <div className="card-body">
                            <Chart type="pie" data={chartData} style={{ width: '160px', height: '200px' }} />
                        </div>

                    </div>
                </div>
                <div className="col-md-6">
                    Bar Chart
                    <div className="card">
                        <div className="card-header">

                        </div>
                        <div className="card-body">
                            <Chart type="bar" data={chartData} className="w-full md:w-20rem" />
                        </div>

                    </div>
                </div>
            </div>
        </div>
    )
}

export default ChartDashboard