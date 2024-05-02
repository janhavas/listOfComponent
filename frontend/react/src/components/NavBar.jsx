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



const NavLink = ({to, children, onClick}) => {
    const handleClick = (event) => {
        // If onClick is provided, call it
        if (onClick) {
            onClick(event);
        }
    };
    return (
        <Box
            as={Link} // Use Link component instead of 'a' tag
            to={to} // Use 'to' prop instead of 'href'
            px={2}
            py={2}
            rounded={'md'}
            _hover={{
                textDecoration: 'none',
                bg: useColorModeValue('green.200', 'blue.700'),
            }}
            onClick={handleClick} // Call handleClick on click event
            >
            {children}
        </Box>
    )
}



export default function NavBar({todayRoute,  fetchOrdersWithCompo, fetchOrdersWithCompoCodNext,handlePrintSelect, handlePrintAll}) {
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
                            <NavLink to="/" >Domov</NavLink>

                            <NavLink onClick={fetchOrdersWithCompo} >Dnes</NavLink>

                            <NavLink onClick={fetchOrdersWithCompoCodNext} >Zajtra</NavLink>
                            <NavLink onClick={handlePrintSelect} >
                                Tlač vybrané
                            </NavLink>
                            <NavLink onClick={handlePrintAll} >
                                 Tlač všetky
                            </NavLink>

                        </HStack>
                    </HStack>
                </Flex>

            </Box>
        </>
    )
}