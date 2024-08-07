import React, { forwardRef } from "react";
import {Table, TableCaption, Tbody, Td, Th, Thead, Tr} from "@chakra-ui/react";
import {render} from "react-dom";



const GeneratePrintTable = forwardRef(({selectedOrders}, ref) =>{

    if (!selectedOrders || selectedOrders.length === 0) {
        return null;
    }

   const columns = Object.keys(selectedOrders[0]);




    return (
            <Table ref={ref} size={"sm"}  variant="simple" style={{ borderSpacing: '0 4px', border: '2px solid green' }}>
                <TableCaption placement={"top"}>Testovacia verzia stránky. Zvážte informácie tu uvedené.</TableCaption>
                <Thead>
                    <Tr>
                        <Th>Číslo objednávky</Th>
                        <Th>Číslo výrobku</Th>
                        <Th>Alt Bom</Th>
                        <Th>Počet</Th>
                        <Th>Technický popis</Th>
                        {/*<Th>Dátum výroby</Th>*/}
                        <Th>Materiál</Th>
                        <Th>Popis materiálu</Th>
                        {/*<Th>Family Code</Th>*/}
                        <Th>Začiatok platnosti</Th>
                        <Th>Koniec platnosti</Th>
                    </Tr>
                </Thead>
                <Tbody>
                    {selectedOrders.map((order, orderIndex) => (
                        <React.Fragment key={orderIndex}>
                            <Tr style={{ border: '2px solid green', padding: '8px' }}>
                                <Td fontSize={25} rowSpan={order.components.length + 1} style={{ textAlign: 'left', verticalAlign: 'top' }}><b>{order.ordNum}</b></Td>
                                <Td rowSpan={order.components.length + 1} style={{ textAlign: 'left', verticalAlign: 'top' }}>{order.fgNum}</Td>
                                <Td rowSpan={order.components.length + 1} style={{ textAlign: 'left', verticalAlign: 'top' }}>{order.altBom}</Td>
                                <Td fontSize={25} rowSpan={order.components.length + 1} style={{ textAlign: 'left', verticalAlign: 'top' }}><b>{order.ordQty}</b></Td>
                                <Td rowSpan={order.components.length + 1} style={{ textAlign: 'left', verticalAlign: 'top' }}><b>{order.indFgDesc}</b></Td>
                               {/* <Td rowSpan={order.components.length + 1} style={{ textAlign: 'left', verticalAlign: 'top' }}>{order.schedDate}</Td>*/}

                            </Tr>
                            {order.components.map((component, index) => (
                                <Tr key={`${order.id}-component-${index}`} style={{ borderBottom: '1px solid green'  }}>
                                    <Td><b>{component.matNum}</b></Td>
                                    <Td>{component.matNumDesc}</Td>
                                    {/*<Td>{component.famCode}</Td>*/}
                                    <Td><b>{component.startDate}</b></Td>
                                    <Td><b>{component.endDate}</b></Td>
                                </Tr>
                            ))}
                        </React.Fragment>
                    ))}
                </Tbody>

            </Table>


    )

});

export default GeneratePrintTable;