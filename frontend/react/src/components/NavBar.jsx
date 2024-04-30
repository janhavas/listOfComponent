import {
    Box,
    Flex,
    Avatar,
    HStack,
    Text,
    IconButton,
    Button,
    Menu,
    MenuButton,
    MenuList,
    MenuItem,
    MenuDivider,
    useDisclosure,
    useColorModeValue,
    Stack, Image,
} from '@chakra-ui/react'
import {Link} from "react-router-dom";

const Links = ['Domov', 'Dnes', 'Zajtra']

const NavLink = ({to, children}) => {
    return (
        <Box
            as={Link} // Use Link component instead of 'a' tag
            to={to} // Use 'to' prop instead of 'href'
            px={2}
            py={1}
            rounded={'md'}
            _hover={{
                textDecoration: 'none',
                bg: useColorModeValue('blue.200', 'blue.700'),
            }}
            href={'#'}>
            {children}
        </Box>
    )
}



export default function NavBar({todayRoute,  fetchOrdersWithCompo, fetchOrdersWithCompoCodNext}) {
    const { isOpen, onOpen, onClose } = useDisclosure()

    return (
        <>
            <Box bg={useColorModeValue('white.100', 'white.900')} px={4}>
                <Flex h={16} alignItems={'center'} justifyContent={'space-between'}>
                    <HStack spacing={8} alignItems={'center'}>
                        <Box>
                            <Image
                                boxSize='100px'
                                objectFit='contain'
                                src='././beko_eu_white_background_one_line.jpg'
                                alt='Beko Europe'
                            />
                        </Box>
                        <HStack as={'nav'} spacing={4} display={{ base: 'none', md: 'flex' }}>
                            <NavLink to="/" px={2} py={1} rounded={'md'} _hover={{ textDecoration: 'none', bg: useColorModeValue('blue.200', 'blue.700') }}>
                                Domov
                            </NavLink>
                            <NavLink to={todayRoute} onClick={fetchOrdersWithCompo} px={2} py={1} rounded={'md'} _hover={{ textDecoration: 'none', bg: useColorModeValue('blue.200', 'blue.700') }}>
                                Dnes
                            </NavLink>
                            <NavLink to={todayRoute}  onClick={fetchOrdersWithCompoCodNext} px={2} py={1} rounded={'md'} _hover={{ textDecoration: 'none', bg: useColorModeValue('blue.200', 'blue.700') }}>
                                Zajtra
                            </NavLink>

                        </HStack>
                    </HStack>
                </Flex>

            </Box>
        </>
    )
}