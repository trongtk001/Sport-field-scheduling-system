import { useState } from "react";

import { Box, Grid, List, ListItemButton, ListItemIcon, ListItemText } from "@mui/material";
import { Container } from "@mui/system";
import { Footer, Header } from "../../components";
import { AccountCircleOutlined, History, SupportAgent } from '@mui/icons-material';
import { useNavigate } from "react-router-dom";


import classNames from "classnames/bind";
import styles from "./UserProfileLayout.module.scss";
const cx = classNames.bind(styles);

function UserProfileLayout({ children }) {


    const [selectedIndex, setSelectedIndex] = useState(0);
    const navigate = useNavigate();

    const handleListItemClick = (event, index) => {
        setSelectedIndex(index);
        switch(index) {
            case 0: 
                navigate('/user');
                break;
            case 1: 
                navigate('/user/history');
                break;
            case 2:
                navigate('/user/support');
                break;
            default:
                console.log('error');
        }
    };

    return (
        <>
            <Header />
            <Container maxWidth='xl'>
                <Grid container spacing={2}>
                    <Grid item xs={4} md={2}>
                        <List>
                            <ListItemButton
                                className={cx('siteBarBtn')}
                                onClick={(event) => handleListItemClick(event, 0)}
                            >
                                <ListItemIcon>
                                    <AccountCircleOutlined color={selectedIndex === 0 ? "success" : ""}/>
                                </ListItemIcon>
                                <ListItemText className={selectedIndex === 0 ? cx('green') : ''}>
                                    Hồ sơ
                                </ListItemText>
                            </ListItemButton>
                            <ListItemButton
                                onClick={(event) => handleListItemClick(event, 1)}
                            >
                                <ListItemIcon>
                                    <History color={selectedIndex === 1 ? "success" : ""}/>
                                </ListItemIcon>
                                <ListItemText className={selectedIndex === 1 ? cx('green') : ''}>
                                    Lịch sử
                                </ListItemText>
                            </ListItemButton>
                            <ListItemButton
                                onClick={(event) => handleListItemClick(event, 2)}
                            >
                                <ListItemIcon>
                                    <SupportAgent color={selectedIndex === 2 ? "success" : ""} />
                                </ListItemIcon>
                                <ListItemText className={selectedIndex === 2 ? cx('green') : ''}>
                                    Hỗ trợ
                                </ListItemText>
                            </ListItemButton>
                        </List>
                    </Grid>
                    <Grid item xs={8} md={10}>
                       <Box className={cx("content")}>{children}</Box>
                    </Grid>
                </Grid>
            </Container>
            <Footer />
        </>
    );
}

export default UserProfileLayout;