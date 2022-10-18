import { Link, useNavigate } from "react-router-dom";
import { useEffect, useRef, useState } from "react";

import { useDispatch, useSelector } from "react-redux/es/exports";
import { filterSlice } from "../../redux/redux-reducer/filter";

import { countCartItemSelector } from "../../redux/redux-selector/cart";

import { Button, Navbar } from "react-bootstrap";
import { Avatar, Badge, ClickAwayListener, Grow, IconButton, MenuItem, MenuList, Paper, Popper } from "@mui/material";
import { ShoppingCart } from "@mui/icons-material"
import { green } from "@mui/material/colors";

import SearchBox from "./SearchBox";
import { logoutService } from "../../Service/authenticationService";
import Cart from "../Cart";


import classNames from "classnames/bind";
import styles from "./NavBar.module.scss";
import { cartSlice } from "../../redux/redux-reducer/cart";
import { getUserInfo } from "../../Service/userService";
import { useAuth } from "../../auth/AuthProvider";
const cx = classNames.bind(styles);



function Header() {

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const auth = useAuth();

  const userID = window.sessionStorage.getItem('userID');
  const [username, setUsername] = useState('');
  const [avatar, setAvatar] = useState('');
  useEffect(() => {
    userID && getUserInfo(userID).then(data => {
      setUsername(data.fullName)
      setAvatar(data.avatar)
    }).catch(error => {
      if (error.response.status === 403) {
        handleLogout()
      }
    })
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [userID])

  const cartItem = useSelector(countCartItemSelector);

  const handleHomeClick = () => {
    dispatch(filterSlice.actions.addFilter({
      type: 'all',
      place: 'all'
    }))
  }

  //user menu
  const [openMenu, setOpenMenu] = useState(false);
  const anchorRef = useRef();

  const handleToggle = () => {
    setOpenMenu((prevOpen) => !prevOpen);
  };

  const handleClose = (event) => {
    if (anchorRef.current.contains(event.target)) {
      return;
    }

    setOpenMenu(false);
  };
  //end

  const handleLogout = () => {
    navigate('/')
    window.sessionStorage.removeItem("userID");
    window.sessionStorage.removeItem("username");
    auth.logout();
    dispatch(cartSlice.actions.clearItem());
    logoutService();
  }

  const [cartHide, setCartHide] = useState(true);
  const handleCartVisible = () => {
    setCartHide(!cartHide);
  }

  return (
    <>

      {cartHide || <Cart action={handleCartVisible} />}

      <Navbar className={cx("nav")} bg="light" expand="lg">
        <Link className={"navbar-brand"} to="/" onClick={handleHomeClick}>
          <img className={cx("icon")} src="../favicon.ico" alt="icon" />
        </Link>

        <Link className="nav-link link-success" to="/" onClick={handleHomeClick}>
          Home
        </Link>


        <Navbar.Collapse className={cx("action")} id="basic-navbar-nav">

          <SearchBox />

        </Navbar.Collapse>
        {!userID ? (<div className={cx("authentication")}>
          <Button as={Link} to="/login" className={cx("login-btn")} variant="light">Log in</Button>

          <Button as={Link} to="/signup" variant="success">Sign up</Button>
        </div>) : (
          <>
            <IconButton sx={{ marginX: 1, }} onClick={handleCartVisible}>
              <Badge badgeContent={cartItem} color="success" showZero
                sx={{
                  '& .MuiBadge-badge': {
                    right: -3,
                    top: 13,
                    border: `2px solid #fff`,
                    padding: '0 4px',
                  },
                }}>
                <ShoppingCart />
              </Badge>
            </IconButton>

            <Avatar onClick={handleToggle} ref={anchorRef} sx={{ marginX: 1, bgcolor: green[500] }} src={avatar}>{username.substring(0, 1).toUpperCase()}</Avatar>

            <Popper open={openMenu} anchorEl={anchorRef.current} role={undefined} placeholder="bottom-start" transition disablePortal>
              {({ TransitionProps, placement }) => (
                <Grow
                  {...TransitionProps}
                  style={{
                    transformOrigin:
                      placement === 'bottom-start' ? 'left top' : 'left bottom',
                  }}
                >
                  <Paper>
                    <ClickAwayListener onClickAway={handleClose}>
                      <MenuList
                        autoFocusItem={openMenu}
                        id="composition-menu"
                        aria-labelledby="composition-button"
                      >
                        <MenuItem onClick={handleClose}><span onClick={() => navigate('/user')}>Profile</span></MenuItem>
                        <MenuItem onClick={handleClose}><span onClick={handleLogout}>Logout</span></MenuItem>
                      </MenuList>
                    </ClickAwayListener>
                  </Paper>
                </Grow>
              )}
            </Popper>
          </>
        )
        }
        <Navbar.Toggle />
      </Navbar>


    </>
  );
}

export default Header;
