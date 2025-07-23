import axios from "axios";

export const getAllOrdersWithCompo = async (workstation) => {

    try {
        return await axios.post(`${import.meta.env.VITE_API_BASE_URL}/api/v1/get-orders-with-components`, workstation);
    } catch (e) {
        throw e;
    }
}


export const getAllOrdersFromCodCell = async () => {

    try {
        return await axios.get(`${import.meta.env.VITE_API_BASE_URL}/api/v1/get-orders-codcell`)
    } catch (e) {
        throw e;
    }

}

export const getAllFilteredOrdersWithCompo = async (orders, workstation) => {

    try {
        const requestData = {
            orders: orders,
            wksId: workstation.wksId
        };

        return await axios.post(`${import.meta.env.VITE_API_BASE_URL}/api/v1/get-orders-with-components/drawerfilter`, requestData);
    } catch (e) {
        throw e;
    }
}