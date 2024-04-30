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

const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>,
    },
    {
        path: "/codpanel",
        element: <CodPanel/>,
    },
    {
        path: "/codskrina",
        element: <CodSkrina/>,
    },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <ChakraProvider>
            <RouterProvider router={router} />
        </ChakraProvider>
    </React.StrictMode>,
)
