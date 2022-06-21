import { useState, useEffect } from "react";
import { Col, Container, Row } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux/es/exports";
import classNames from "classnames/bind";

import styles from "./home.module.scss";
import { FieldList } from "../../components";
import getFieldList from "../../Service/getFieldService";
import { addFilter } from "../../redux/redux-actions";
import { filterSelector } from "../../redux/redux-selector/fiedlList";

const cx = classNames.bind(styles);

function Home() {

  const dispatch = useDispatch();

  const filter = useSelector(filterSelector)

  const [fieldData, setFieldData] = useState({ list: [] });

  useEffect(() => {
    getFieldList(filter.type).then(setFieldData)
  }, [filter]);

  const handleFilter = (e) => {
    dispatch(addFilter({ type: e.target.value, place: '' }))
  }

  return (
    <div className={cx("home")}>
      <div className={cx("slide")}>
        <img src="../slice.png" alt="" />
      </div>

      <Container>
        <Row>
          <Col className={cx("left-container")} md="3">
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
          <Col className={cx("primary-container")} md="9">
            {fieldData.list.length ? (
              <FieldList data={fieldData.list} />
            ) : (
              <h2>Không có sân nào</h2>
            )}
          </Col>
        </Row>
      </Container>
    </div>
  );
}

export default Home;
