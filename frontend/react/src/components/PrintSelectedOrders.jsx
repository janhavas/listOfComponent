import {Box, Button, Container, Table, TableCaption, Tbody, Td, Th, Thead, Tr} from "@chakra-ui/react";
import React, {useRef, useState} from "react";
import { useReactToPrint } from "react-to-print";
import GeneratePrintTable from "./GeneratePrintTable.jsx";


const PrintSelectedOrders = ({ selectedOrders }) => {
    const [isPrinting, setIsPrinting] = useState(false);
    const ordersToPrint = useRef();
    const generatePrintOutput = useReactToPrint({
        content: ()=>ordersToPrint.current,
        documentTitle: "Print Orders With Components",
        onBeforeGetContent: () => {
            // Set isPrinting to true before generating content
            setIsPrinting(true);
        },


        onAfterPrint: () => {
            // Set isPrinting to false after printing is complete
            setIsPrinting(false);
        },
    });

    const handlePrintSelectedRows = () => {
        setIsPrinting(true);
        generatePrintOutput();

    };

    return (
        <>
            <Container maxW='8xl'>
                <Box padding='4'>
                    <Button onClick={ handlePrintSelectedRows } colorScheme='green'>Print Selected Rows</Button>
                </Box>
            </Container>

            {isPrinting && (
                <div style={{ display: 'none' }}>
                    <div ref={ordersToPrint}>
                        <GeneratePrintTable selectedOrders={selectedOrders} />
                    </div>
                </div>
            )}
        </>
    )

}

export default PrintSelectedOrders