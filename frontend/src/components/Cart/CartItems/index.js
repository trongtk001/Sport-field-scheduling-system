
import { List } from "@mui/material";


import Item from "./Item";

function CartItems({ data }) {

    return (
        <List sx={{ height: "90%", overflow: 'auto', boxShadow: "rgba(0, 0, 0, 0.05) 0px 6px 24px 0px, rgba(0, 0, 0, 0.08) 0px 0px 0px 1px" }}>
            <h3>DANH SÁCH SÂN</h3>
                {
                        data.map(item => (
                            <Item key={item.field.id} data={item}/>
                            ))
                }
        </List>
    );
}

export default CartItems;