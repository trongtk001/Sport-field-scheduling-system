import { Col, Container, FormSelect, Row } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux/es/exports";
import classNames from "classnames/bind";

import styles from "./SearchPage.module.scss";
import { Cart, FieldList } from "../../components";
import {
  searchValueSelector,
} from "../../redux/redux-selector/filter";
import { useEffect, useState } from "react";
import { searchHavingTypeService, searchService } from "../../Service/searchService";
import { Fade } from "@mui/material";
import { cartSlice } from "../../redux/redux-reducer/cart";

const cx = classNames.bind(styles);

function SearchPage() {

  const dispatch = useDispatch();

  const searchValue = useSelector(searchValueSelector);

  const [searchResult, setSearchResult] = useState({ list: [] })
  const [type, setType] = useState('all');
  // const [place, setPlace] = useState('all')





  useEffect(() => {
    if (type !== "all") {
      searchHavingTypeService(searchValue, type).then(setSearchResult)
    } else {
      searchService(searchValue, type).then(setSearchResult)
    }
  }, [type, searchValue])



  const [cartHide, setCartHide] = useState(false);

  const handleCartVisible = () => {
    setCartHide(!cartHide);
  }

  const rentClickHandle = (field) => {
    handleCartVisible();
    dispatch(cartSlice.actions.addItem({ field: field, time: 1 }))
  }

  return (
    <>
      <Container>
        <Fade in={cartHide}>
          <div>
            <Cart action={handleCartVisible} />
          </div>
        </Fade>
        <Row className={cx("selection")}>
          <Col md="3">
            <FormSelect value={type} onChange={(e) => setType(e.target.value)}>
              <option value="all">Loại sân</option>
              <option value="soccer">Bóng đá</option>
              <option value="basketball">Bóng rổ</option>
              <option value="tennis">Tennis</option>
            </FormSelect>
          </Col>

          {/* <Col md="3">
            <FormSelect value={place} onChange={(e) => setPlace(e.target.value)}>
              <option value="DEFAULT">Địa điểm</option>
              <option value="ngoaithanh">Ngoại Thành</option>
              <option value="noithanh">Nội Thành</option>
            </FormSelect>
          </Col> */}
        </Row>
        <Row className={cx('search')}>{searchValue && <h2>Search: {searchValue}</h2>}</Row>

        <Row>{(searchResult.list.length) ? <FieldList data={searchResult.list} action={rentClickHandle}/> : <h2>Không có sân nào</h2>}</Row>
      </Container>
    </>
  );
}

export default SearchPage;
