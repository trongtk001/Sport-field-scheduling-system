import { Alert, Snackbar } from "@mui/material";
import { useState } from "react";

function SuccessSnackbar() {

    const [open, setOpen] = useState(true);

    const handleClose = () => {
        setOpen(false)
    }

    return (
        <Snackbar open={open} autoHideDuration={3000} onClose={handleClose}>
            <Alert onClick={handleClose} severity="success">This is a success message!</Alert>
        </Snackbar>
    );
}

export default SuccessSnackbar;