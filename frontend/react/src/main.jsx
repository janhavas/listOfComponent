import React from 'react'
import ReactDOM from 'react-dom/client'
import {ChakraProvider} from '@chakra-ui/react'
import './index.css'
import App from "./App.jsx";
import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";
import CodPanel from "./Pages/CodPanel.jsx";
import CodSkrina from "./Pages/CodSkrina.jsx";
import FlexSkrina from "./Pages/FlexSkrina.jsx";
import FlexPanel from "./Pages/FlexPanel.jsx";
import FlexVodna from "./Pages/FlexVodna.jsx";
import FlexAgregat from "./Pages/FlexAgregat.jsx";
import CodVodna from "./Pages/CodVodna.jsx";
import CodAgregat from "./Pages/CodAgregat.jsx";

const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>,
    },
    {
        path: "/codagregat",
        element: <CodAgregat/>,
    },
    {
        path: "/codpanel",
        element: <CodPanel/>,
    },
    {
        path: "/codskrina",
        element: <CodSkrina/>,
    },
    {
        path: "/codvodna",
        element: <CodVodna/>,
    },
    {
        path: "/flexagregat",
        element: <FlexAgregat/>,
    },
    {
        path: "/flexskrina",
        element: <FlexSkrina/>,
    },
    {
        path: "/flexpanel",
        element: <FlexPanel/>,
    },
    {
        path: "/flexvodna",
        element: <FlexVodna/>,
    },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <ChakraProvider>
            <RouterProvider router={router} />
        </ChakraProvider>
    </React.StrictMode>,
)
