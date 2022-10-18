import { useState, useEffect } from "react";

import { Col, Container, Row } from "react-bootstrap";
import { Fade, Pagination } from "@mui/material";


import { useDispatch, useSelector } from "react-redux/es/exports";
import { filterSelector } from "../../redux/redux-selector/filter";
import { filterSlice } from "../../redux/redux-reducer/filter";
import { cartSlice } from "../../redux/redux-reducer/cart";


import { Cart, FieldList } from "../../components";
import { getFieldList } from "../../Service/getFieldService";

import classNames from "classnames/bind";
import styles from "./home.module.scss";
const cx = classNames.bind(styles);

function Home() {

  const dispatch = useDispatch();

  const filter = useSelector(filterSelector)
  const [page, setPage] = useState(1);

  const [fieldData, setFieldData] = useState({ list: [] });

  const [cartHide, setCartHide] = useState(false);



  useEffect(() => {
    getFieldList(filter.type, page).then(setFieldData)
  }, [filter, cartHide, page]);

  const handleFilter = (e) => {
    dispatch(filterSlice.actions.addFilter({ type: e.target.value, place: "" }))
  }

  const handleCartVisible = () => {
    setCartHide(!cartHide);
  }

  const rentClickHandle = (field) => {
    handleCartVisible();
    dispatch(cartSlice.actions.addItem({ field: field, time: 1 }))
  }


  return (
    <div className={cx("home")}>
      <div className={cx("slide")}>
        <img src="../slice.png" alt="" />
      </div>
      <Container>
        <Fade in={cartHide}>
          <div>
            <Cart action={handleCartVisible}/>
          </div>
        </Fade>
        <Row>
          <Col className={cx("left-container")} md="2">
            <ul>
              <li>
                <button value={'soccer'} onClick={handleFilter} >BÓNG ĐÁ</button>
              </li>
              <li>
                <button value={'basketball'} onClick={handleFilter} >BÓNG RỔ</button>
              </li>
              <li>
                <button value={'tennis'} onClick={handleFilter} >TENNIS</button>
              </li>
            </ul>
          </Col>
          <Col className={cx("primary-container")} md="10">
            {fieldData.list.length ? (
              <FieldList data={fieldData.list} action={rentClickHandle} />
            ) : (
              <h2>Không có sân nào</h2>
            )}
            <div className="center"><Pagination count={fieldData.totalPage ? fieldData.totalPage : 1} page={page} onChange={(e, page) => setPage(page)} /></div>
          </Col>
        </Row>
      </Container>
    </div>
  );
}

export default Home;
