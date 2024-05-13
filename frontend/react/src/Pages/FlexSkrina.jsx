import NavBar from "../components/NavBar.jsx";
import {useRef, useState} from "react";
import {getAllOrdersWithCompo, getAllOrdersWithCompoCodNext} from "../services/order.js";


function FlexSkrina(){

    const [orderscompo, setOrderscompo] = useState([]);
    const [loading, setLoading] = useState(false);
    const [selectedRows, setSelectedRows] = useState([]);
    const componentRef = useRef();
    const componentRefAll = useRef();

    const fetchOrdersWithCompo = () => {
        setLoading(true);
        getAllOrdersWithCompo().then(res => {
            setOrderscompo(res.data)
            console.log("Default data: " + JSON.stringify(res))
            console.log("Received components:", JSON.stringify(res.components))
        }).catch(err => {
            console.log(err)
        }).finally(() => {
            setLoading(false)
        })
    }

    const fetchOrdersWithCompoCodNext = () => {
        setLoading(true);
        getAllOrdersWithCompoCodNext().then(res => {
            setOrderscompo([]);
            setOrderscompo(res.data)
            console.log("Default data: " + JSON.stringify(res))
            console.log("Received components:", JSON.stringify(res.components))
        }).catch(err => {
            console.log(err)
        }).finally(() => {
            setLoading(false)
        })
    }

    return (
        <>
            <NavBar todayRoute={"/codskrina"} />

            <div>Cod Panel</div>
        </>
    )

}

export default FlexSkrina