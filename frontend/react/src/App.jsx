import {
    Box,
    Button,
    Card, CardBody,
    CardFooter,
    CardHeader,
    Center,
    Heading,
    Image,
    Stack,
    Text,
    useColorModeValue,
    Wrap,
    WrapItem,
} from '@chakra-ui/react'
import {Link} from "react-router-dom";
export default function App() {
    return (
        <>

            <Center py={6}>
                <Wrap justify="center">
                    <WrapItem>
                        <Box>
                            <Image
                                src='./beko_eu_white_background_one_line.jpg'
                                alt='Beko Europe'
                            />
                        </Box>
                    </WrapItem>
                    <WrapItem>
                        <Box
                            maxW={'480px'}
                            w={'full'}
                            bg={useColorModeValue('white', 'gray.800')}
                            boxShadow={'2xl'}
                            rounded={'md'}
                            overflow={'hidden'}>

                            <Stack
                                textAlign={'center'}
                                p={6}
                                color={useColorModeValue('gray.800', 'white')}
                                align={'center'}>

                                <Stack direction={'row'} align={'center'} justify={'center'}>
                                    <Text fontSize={'6xl'} fontWeight={800}>
                                        COD
                                    </Text>
                                    <Text fontSize={'3xl'}>Linka</Text>


                                </Stack>
                            </Stack>

                            <Box bg={useColorModeValue('gray.50', 'gray.900')} px={6} py={10}>
                                <Wrap>
                                    <WrapItem>

                                        <Card>
                                            <CardHeader>
                                                <Heading size='md'> Skriňa</Heading>
                                                <Button
                                                    mt={10}
                                                    w={'full'}
                                                    bg={'green.400'}
                                                    color={'white'}
                                                    rounded={'xl'}
                                                    boxShadow={'0 5px 20px 0px rgb(72 187 120 / 43%)'}
                                                    _hover={{
                                                        bg: 'green.500',
                                                    }}
                                                    _focus={{
                                                        bg: 'green.500',
                                                    }}
                                                    >
                                                    <Link to={"/codskrina"}>Zobraz materiál</Link>
                                                </Button>

                                            </CardHeader>

                                        </Card>
                                    </WrapItem>
                                    <WrapItem>
                                        <Card>
                                            <CardHeader>
                                                <Heading size='md'> Panel</Heading>
                                                <Button
                                                    mt={10}
                                                    w={'full'}
                                                    bg={'green.400'}
                                                    color={'white'}
                                                    rounded={'xl'}
                                                    boxShadow={'0 5px 20px 0px rgb(72 187 120 / 43%)'}
                                                    _hover={{
                                                        bg: 'green.500',
                                                    }}
                                                    _focus={{
                                                        bg: 'green.500',
                                                    }}>
                                                    <Link to={"/codpanel"}>Zobraz materiál</Link>
                                                </Button>
                                            </CardHeader>

                                        </Card>
                                    </WrapItem>
                                    <WrapItem>
                                        <Card>
                                            <CardHeader>
                                                <Heading size='md'> Pracovisko</Heading>
                                                <Button
                                                    mt={10}
                                                    w={'full'}
                                                    bg={'green.400'}
                                                    color={'white'}
                                                    rounded={'xl'}
                                                    boxShadow={'0 5px 20px 0px rgb(72 187 120 / 43%)'}
                                                    _hover={{
                                                        bg: 'green.500',
                                                    }}
                                                    _focus={{
                                                        bg: 'green.500',
                                                    }}>
                                                    Zobraz objednávky
                                                </Button>
                                            </CardHeader>

                                        </Card>
                                    </WrapItem>
                                </Wrap>

                            </Box>
                        </Box>
                    </WrapItem>
                    <WrapItem>
                        <Box
                            maxW={'630px'}
                            w={'full'}
                            bg={useColorModeValue('white', 'gray.800')}
                            boxShadow={'2xl'}
                            rounded={'md'}
                            overflow={'hidden'}>
                            <Stack
                                textAlign={'center'}
                                p={6}
                                color={useColorModeValue('gray.800', 'white')}
                                align={'center'}>

                                <Stack direction={'row'} align={'center'} justify={'center'}>
                                    <Text fontSize={'6xl'} fontWeight={800}>
                                        FLEX
                                    </Text>
                                    <Text fontSize={'3xl'}>Linka</Text>


                                </Stack>
                            </Stack>

                            <Box bg={useColorModeValue('gray.50', 'gray.900')} px={6} py={10}>
                                <Wrap>
                                    <WrapItem>
                                        <Card>
                                            <CardHeader>
                                                <Heading size='md'> Skriňa</Heading>
                                                <Button
                                                    mt={10}
                                                    w={'full'}
                                                    bg={'green.400'}
                                                    color={'white'}
                                                    rounded={'xl'}
                                                    boxShadow={'0 5px 20px 0px rgb(72 187 120 / 43%)'}
                                                    _hover={{
                                                        bg: 'green.500',
                                                    }}
                                                    _focus={{
                                                        bg: 'green.500',
                                                    }}>
                                                    Zobraz objednávky
                                                </Button>
                                            </CardHeader>
                                        </Card>
                                    </WrapItem>
                                    <WrapItem>
                                        <Card>
                                            <CardHeader>
                                                <Heading size='md'> Panel</Heading>
                                                <Button
                                                    mt={10}
                                                    w={'full'}
                                                    bg={'green.400'}
                                                    color={'white'}
                                                    rounded={'xl'}
                                                    boxShadow={'0 5px 20px 0px rgb(72 187 120 / 43%)'}
                                                    _hover={{
                                                        bg: 'green.500',
                                                    }}
                                                    _focus={{
                                                        bg: 'green.500',
                                                    }}>
                                                    Zobraz objednávky
                                                </Button>
                                            </CardHeader>
                                        </Card>
                                    </WrapItem>
                                </Wrap>
                            </Box>
                        </Box>
                    </WrapItem>
                    <WrapItem>
                        <Box
                            maxW={'480px'}
                            w={'full'}
                            bg={useColorModeValue('white', 'gray.800')}
                            boxShadow={'2xl'}
                            rounded={'md'}
                            overflow={'hidden'}>

                            <Stack
                                textAlign={'center'}
                                p={6}
                                color={useColorModeValue('gray.800', 'white')}
                                align={'center'}>

                                <Stack direction={'row'} align={'center'} justify={'center'}>
                                    <Text fontSize={'6xl'} fontWeight={800}>
                                        HR
                                    </Text>
                                    <Text fontSize={'3xl'}>Linka</Text>


                                </Stack>
                            </Stack>

                            <Box bg={useColorModeValue('gray.50', 'gray.900')} px={6} py={10}>
                                <Wrap>
                                    <WrapItem>

                                        <Card>
                                            <CardHeader>
                                                <Heading size='md'> Skriňa</Heading>
                                                <Button
                                                    mt={10}
                                                    w={'full'}
                                                    bg={'green.400'}
                                                    color={'white'}
                                                    rounded={'xl'}
                                                    boxShadow={'0 5px 20px 0px rgb(72 187 120 / 43%)'}
                                                    _hover={{
                                                        bg: 'green.500',
                                                    }}
                                                    _focus={{
                                                        bg: 'green.500',
                                                    }}>
                                                    Zobraz objednávky
                                                </Button>
                                            </CardHeader>
                                        </Card>
                                    </WrapItem>
                                    <WrapItem>
                                        <Card>
                                            <CardHeader>
                                                <Heading size='md'> Panel</Heading>
                                                <Button
                                                    mt={10}
                                                    w={'full'}
                                                    bg={'green.400'}
                                                    color={'white'}
                                                    rounded={'xl'}
                                                    boxShadow={'0 5px 20px 0px rgb(72 187 120 / 43%)'}
                                                    _hover={{
                                                        bg: 'green.500',
                                                    }}
                                                    _focus={{
                                                        bg: 'green.500',
                                                    }}>
                                                    Zobraz objednávky
                                                </Button>
                                            </CardHeader>
                                        </Card>
                                    </WrapItem>
                                    <WrapItem>
                                        <Card>
                                            <CardHeader>
                                                <Heading size='md'> Pracovisko</Heading>
                                                <Button
                                                    mt={10}
                                                    w={'full'}
                                                    bg={'green.400'}
                                                    color={'white'}
                                                    rounded={'xl'}
                                                    boxShadow={'0 5px 20px 0px rgb(72 187 120 / 43%)'}
                                                    _hover={{
                                                        bg: 'green.500',
                                                    }}
                                                    _focus={{
                                                        bg: 'green.500',
                                                    }}>
                                                    Zobraz objednávky
                                                </Button>
                                            </CardHeader>
                                        </Card>
                                    </WrapItem>
                                </Wrap>

                            </Box>
                        </Box>
                    </WrapItem>
                    <WrapItem>
                        <Box
                            maxW={'480px'}
                            w={'full'}
                            bg={useColorModeValue('white', 'gray.800')}
                            boxShadow={'2xl'}
                            rounded={'md'}
                            overflow={'hidden'}>

                            <Stack
                                textAlign={'center'}
                                p={6}
                                color={useColorModeValue('gray.800', 'white')}
                                align={'center'}>

                                <Stack direction={'row'} align={'center'} justify={'center'}>
                                    <Text fontSize={'6xl'} fontWeight={800}>
                                        LR
                                    </Text>
                                    <Text fontSize={'3xl'}>Linka</Text>


                                </Stack>
                            </Stack>

                            <Box bg={useColorModeValue('gray.50', 'gray.900')} px={6} py={10}>
                                <Wrap>
                                    <WrapItem>

                                        <Card>
                                            <CardHeader>
                                                <Heading size='md'> Skriňa</Heading>
                                                <Button
                                                    mt={10}
                                                    w={'full'}
                                                    bg={'green.400'}
                                                    color={'white'}
                                                    rounded={'xl'}
                                                    boxShadow={'0 5px 20px 0px rgb(72 187 120 / 43%)'}
                                                    _hover={{
                                                        bg: 'green.500',
                                                    }}
                                                    _focus={{
                                                        bg: 'green.500',
                                                    }}>
                                                    Zobraz objednávky
                                                </Button>
                                            </CardHeader>
                                        </Card>
                                    </WrapItem>
                                    <WrapItem>
                                        <Card>
                                            <CardHeader>
                                                <Heading size='md'> Panel</Heading>
                                                <Button
                                                    mt={10}
                                                    w={'full'}
                                                    bg={'green.400'}
                                                    color={'white'}
                                                    rounded={'xl'}
                                                    boxShadow={'0 5px 20px 0px rgb(72 187 120 / 43%)'}
                                                    _hover={{
                                                        bg: 'green.500',
                                                    }}
                                                    _focus={{
                                                        bg: 'green.500',
                                                    }}>
                                                    Zobraz objednávky
                                                </Button>
                                            </CardHeader>
                                        </Card>
                                    </WrapItem>
                                    <WrapItem>
                                        <Card>
                                            <CardHeader>
                                                <Heading size='md'> Pracovisko</Heading>
                                                <Button
                                                    mt={10}
                                                    w={'full'}
                                                    bg={'green.400'}
                                                    color={'white'}
                                                    rounded={'xl'}
                                                    boxShadow={'0 5px 20px 0px rgb(72 187 120 / 43%)'}
                                                    _hover={{
                                                        bg: 'green.500',
                                                    }}
                                                    _focus={{
                                                        bg: 'green.500',
                                                    }}>
                                                    Zobraz objednávky
                                                </Button>
                                            </CardHeader>
                                        </Card>
                                    </WrapItem>
                                </Wrap>

                            </Box>
                        </Box>
                    </WrapItem>
                </Wrap>
            </Center>
        </>
    )
}

