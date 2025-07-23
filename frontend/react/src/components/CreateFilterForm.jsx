
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import {Button} from "@chakra-ui/react";


const DynamicOrdersForm = ({ fetchFilteredOrdersWithCompo }) => {

    const initialValues = {
        ordersInput: ''
    };

    const validationSchema = Yup.object().shape({
        ordersInput: Yup.string()
            .required('Zadaj objednávku.')
            .test('is-valid-orders', 'Objednávka musí mať presne 9 čísel.', (value) => {
                if (!value) return false;
                const orders = value.split('\n').map((order) => order.trim());
                return orders.every((order) => /^\d{9}$/.test(order)); // kontroluje, či každá hodnota obsahuje presne 10 číslic
            })
    });

    const onSubmit = (values, { setSubmitting }) => {
        setSubmitting(true);
        const filteredOrders = values.ordersInput.split('\n').map((order) => order.trim());
        fetchFilteredOrdersWithCompo(filteredOrders);
        console.log('Parsed Orders:', filteredOrders);
        // You can now handle the orders array as needed
        setSubmitting(false);
    };



    return (
        <Formik
            initialValues={initialValues}
            validationSchema={validationSchema}
            onSubmit={onSubmit}
        >
            {({ isValid, isSubmitting }) => (
                <Form>
                    <div>
                        <label htmlFor="ordersInput">Zadajte objednávky (jednu na riadok):</label>
                        <Field
                            as="textarea"
                            id="ordersInput"
                            name="ordersInput"
                            rows="15"
                            cols="50"
                            style={{borderSpacing: '0 4px', border: '2px solid green', borderRadius: '8px'}}
                        />
                        <ErrorMessage name="ordersInput" component="div" />
                    </div>
                    <Button disabled={!isValid || isSubmitting} type="submit">
                        Odoslať
                    </Button>
                </Form>
            )}
        </Formik>
    );
};



const CreateFilterForm = ({ fetchFilteredOrdersWithCompo }) => {

    return (
        <div>

            <DynamicOrdersForm
                fetchFilteredOrdersWithCompo={fetchFilteredOrdersWithCompo}
            >
            </DynamicOrdersForm>
        </div>
    );
};


export default CreateFilterForm;