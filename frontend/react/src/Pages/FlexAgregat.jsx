import NavBar from "../components/NavBar.jsx";
import React, {useEffect, useRef, useState} from "react";
import {getAllOrdersWithCompo} from "../services/order.js";
import {useReactToPrint} from "react-to-print";
import {
    Checkbox,
    Flex,
    Heading,
    Highlight,
    SimpleGrid,
    Spinner,
    Table,
    TableCaption,
    TableContainer,
    Tbody,
    Td,
    Text,
    Th,
    Thead,
    Tr
} from "@chakra-ui/react";
import GeneratePrintTable from "../components/GeneratePrintTable.jsx";


function FlexAgregat() {

    const [orderscompo, setOrderscompo] = useState([]);
    const [loading, setLoading] = useState(false);
    const [selectedRows, setSelectedRows] = useState([]);
    const componentRef = useRef();
    const componentRefAll = useRef();
    const workstation = {wksId: '0201'};

    const marginTop = "10px"
    const marginRight = "5px"
    const marginBottom = "10px"
    const marginLeft = "5px"

    const fetchOrdersWithCompo = () => {
        setLoading(true);
        workstation.date = 'dnes';
        getAllOrdersWithCompo(workstation).then(res => {
            setOrderscompo(res.data)
            /*            console.log("Default data: " + JSON.stringify(res))
                        console.log("Received components:", JSON.stringify(res.components))*/
        }).catch(err => {
            console.log(err)
        }).finally(() => {
            delete workstation.date;
            setLoading(false)
        })
    }

    const fetchOrdersWithCompoCodNext = () => {
        setLoading(true);
        workstation.date = 'zajtra';
        getAllOrdersWithCompo(workstation).then(res => {
            setOrderscompo([]);
            setOrderscompo(res.data)
            /*            console.log("Default data: " + JSON.stringify(res))
                        console.log("Received components:", JSON.stringify(res.components))*/
        }).catch(err => {
            console.log(err)
        }).finally(() => {
            delete workstation.date;
            setLoading(false)
        })
    }


    const handlePrintSelect = useReactToPrint({
        content: () => componentRef.current,
        onAfterPrint: () => {
            setSelectedRows([]);
            fetchOrdersWithCompo();
            console.log("PRINTING COMPLETE");
        }

    });

    const handlePrintAll = useReactToPrint({
        content: () => componentRefAll.current,

    });

    useEffect(() => {
        fetchOrdersWithCompo();
        /*fetchOrdersWithCompoCodNext();*/
    }, []);


    const getPageMargins = () => {
        return `@page { margin: ${marginTop} ${marginRight} ${marginBottom} ${marginLeft} !important; }`;
    };

    if (loading) {
        return (
            <>
                <NavBar todayRoute={"/flexagregat"} fetchOrdersWithCompo={fetchOrdersWithCompo}
                        fetchOrdersWithCompoCodNext={fetchOrdersWithCompoCodNext}/>
                <SimpleGrid
                    display={"flex"}
                    justifyContent={"center"}
                    alignItems={"center"}
                    height={"100vh"}
                >
                    <Spinner
                        thickness='4px'
                        speed='0.65s'
                        emptyColor='gray.200'
                        color='blue.500'
                        size='xl'
                    />
                </SimpleGrid>
            </>
        )
    }

    if (orderscompo.length <= 0) {
        return (
            <>
                <NavBar todayRoute={"/flexagregat"} fetchOrdersWithCompo={fetchOrdersWithCompo}
                        fetchOrdersWithCompoCodNext={fetchOrdersWithCompoCodNext}/>
                <SimpleGrid>
                    <Text fontSize='2xl'>No Orders</Text>
                </SimpleGrid>
            </>
        )
    }


    return (
        <>
            <div style={{display: "none"}}>
                <GeneratePrintTable selectedOrders={selectedRows} ref={componentRef}/>
            </div>
            <NavBar todayRoute={"/flexagregat"}
                    fetchOrdersWithCompo={fetchOrdersWithCompo}
                    fetchOrdersWithCompoCodNext={fetchOrdersWithCompoCodNext}
                    handlePrintSelect={handlePrintSelect}
                    handlePrintAll={handlePrintAll}
            />
            <Flex alignItems="center" justifyContent="center">
                <Heading lineHeight='tall'>
                    <Highlight
                        query={['FLEX', 'Agregát']}
                        styles={{px: '2', py: '1', rounded: 'full', bg: 'green.100'}}
                    >
                        Montážna linka: FLEX
                        Pracovisko: Agregát
                    </Highlight>
                </Heading>
            </Flex>


            <Flex alignItems="center" justifyContent="center">
                <TableContainer>

                    <Table ref={componentRefAll} size={"sx"} variant="simple"
                           style={{borderSpacing: '0 4px', border: '2px solid green'}}>
                        <TableCaption placement={"top"}>Testovacia verzia stránky. Zvážte informácie tu
                            uvedené.</TableCaption>
                        <Thead>
                            <Tr>
                                <Th minW="60px" maxW="80px" style={{whiteSpace: "pre-wrap", wordWrap: "break-word"}}>Výber
                                    tlače</Th>
                                {/*<Th >Line ID</Th>*/}
                                <Th>Číslo objednávky</Th>
                                <Th minW="110px" maxW="110px" style={{whiteSpace: "pre-wrap", wordWrap: "break-word"}}>Číslo
                                    výrobku</Th>
                                <Th minW="45px" maxW="65px" style={{whiteSpace: "pre-wrap", wordWrap: "break-word"}}>Alt
                                    Bom</Th>
                                <Th minW="65px" maxW="65px"
                                    style={{whiteSpace: "pre-wrap", wordWrap: "break-word"}}>Počet</Th>
                                {/*                        <Th>Individual FG Number</Th>
                        <Th>Individual FG Description</Th>*/}
                                <Th>Technický popis</Th>
                                <Th minW="160px" maxW="160px" style={{whiteSpace: "pre-wrap", wordWrap: "break-word"}}>Dátum
                                    výroby</Th>
                                <Th>Materiál</Th>
                                <Th>Popis materiálu</Th>
                                <Th minW="80px" maxW="80px" style={{whiteSpace: "pre-wrap", wordWrap: "break-word"}}>Family
                                    Code</Th>
                                <Th minW="110px" maxW="120px" style={{whiteSpace: "pre-wrap", wordWrap: "break-word"}}>Začiatok
                                    platnosti</Th>
                                <Th minW="110px" maxW="120px" style={{whiteSpace: "pre-wrap", wordWrap: "break-word"}}>Koniec
                                    platnosti</Th>
                            </Tr>
                        </Thead>
                        <Tbody>
                            {orderscompo.map((order) => (
                                <React.Fragment key={order.id}>
                                    <Tr style={{borderBottom: '2px solid green', borderTop: '2px solid green'}}>
                                        <Td rowSpan={order.components.length + 1}
                                            style={{textAlign: 'left', verticalAlign: 'top'}}>
                                            <Checkbox

                                                onChange={(e) => {
                                                    if (e.target.checked) {
                                                        // Checkbox is checked, add the row data to selectedRows
                                                        setSelectedRows([...selectedRows,
                                                            {
                                                                id: order.id,
                                                                ordNum: order.ordNum,
                                                                fgNum: order.fgNum,
                                                                altBom: order.altBom,
                                                                ordQty: order.ordQty,
                                                                indFgDesc: order.indFgDesc,
                                                                schedDate: order.schedDate,
                                                                components: order.components.map((component) => ({
                                                                    matNum: component.matNum,
                                                                    matNumDesc: component.matNumDesc,
                                                                    famCode: component.famCode,
                                                                    startDate: component.startDate,
                                                                    endDate: component.endDate
                                                                }))
                                                            }]);
                                                    } else {
                                                        // Checkbox is unchecked, remove the row data from selectedRows
                                                        setSelectedRows(selectedRows.filter((row) => row.id !== order.id));
                                                    }
                                                }}
                                            />
                                        </Td>
                                        {/*<Td rowSpan={order.components.length + 1} style={{ textAlign: 'left', verticalAlign: 'top' }}>{order.lineId}</Td>*/}
                                        <Td fontSize={25} rowSpan={order.components.length + 1}
                                            style={{textAlign: 'left', verticalAlign: 'top'}}><b>{order.ordNum}</b></Td>
                                        <Td rowSpan={order.components.length + 1}
                                            style={{textAlign: 'left', verticalAlign: 'top'}}>{order.fgNum}</Td>
                                        <Td rowSpan={order.components.length + 1}
                                            style={{textAlign: 'left', verticalAlign: 'top'}}>{order.altBom}</Td>
                                        <Td fontSize={25} rowSpan={order.components.length + 1}
                                            style={{textAlign: 'left', verticalAlign: 'top'}}><b>{order.ordQty}</b></Td>
                                        {/*                                <Td rowSpan={order.components.length + 1} style={{ textAlign: 'left', verticalAlign: 'top' }}>{order.indFgNum}</Td>
                                <Td rowSpan={order.components.length + 1} style={{ textAlign: 'left', verticalAlign: 'top' }}>{order.indFgDesc}</Td>*/}
                                        <Td rowSpan={order.components.length + 1} style={{
                                            textAlign: 'left',
                                            verticalAlign: 'top'
                                        }}><b>{order.indFgDesc}</b></Td>
                                        <Td rowSpan={order.components.length + 1}
                                            style={{textAlign: 'left', verticalAlign: 'top'}}>{order.schedDate}</Td>


                                    </Tr>
                                    {order.components.map((component, index) => (
                                        <Tr key={`${order.id}-component-${index}`}
                                            style={{borderBottom: '2px solid green', borderTop: '2px solid green'}}>
                                            <Td><b>{component.matNum}</b></Td>
                                            <Td>{component.matNumDesc}</Td>
                                            <Td>{component.famCode}</Td>
                                            <Td><b>{component.startDate}</b></Td>
                                            <Td><b>{component.endDate}</b></Td>
                                        </Tr>
                                    ))}
                                </React.Fragment>
                            ))}
                        </Tbody>
                    </Table>
                    <style>{getPageMargins()}</style>
                </TableContainer>

            </Flex>


        </>
    )

}

export default FlexAgregat