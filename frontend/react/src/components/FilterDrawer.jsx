import {
    Button,
    Drawer,
    DrawerBody,
    DrawerCloseButton,
    DrawerContent, DrawerFooter,
    DrawerHeader,
    DrawerOverlay, Icon, useDisclosure
} from "@chakra-ui/react";
import CreateFilterForm from "./CreateFilterForm.jsx";
import {FaSearch, FaTimes} from "react-icons/fa";

const FilterIcon = () => <Icon as={FaSearch} />
const CloseIcon = () => <Icon as={FaTimes} />
const FilterDrawer = ({ fetchFilteredOrdersWithCompo }) => {
    const { isOpen, onOpen, onClose } = useDisclosure()

    return (
        <>
            <Button
                leftIcon={<FilterIcon/>}
                colorScheme={"gray"}
                onClick={onOpen}
            >
                Filter
            </Button>
            <Drawer isOpen={isOpen} onClose={onClose} size={"md"} placement={"left"}>
                <DrawerOverlay />
                <DrawerContent>
                    <DrawerCloseButton />
                    <DrawerHeader>Filter objednávok</DrawerHeader>

                    <DrawerBody>

                        <CreateFilterForm
                            fetchFilteredOrdersWithCompo={fetchFilteredOrdersWithCompo}
                        >
                        </CreateFilterForm>
                    </DrawerBody>

                    <DrawerFooter>
                        <Button
                            leftIcon={<CloseIcon/>}
                            colorScheme={"red"}
                            onClick={onClose}
                        >
                            Zatvoriť
                        </Button>
                    </DrawerFooter>
                </DrawerContent>
            </Drawer>
        </>
    )
}

export default FilterDrawer;

