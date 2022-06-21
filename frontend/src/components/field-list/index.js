import { Col, Container, Row } from "react-bootstrap";
import classNames from "classnames/bind";

import styles from "./FieldList.module.scss";
const cx = classNames.bind(styles)

function FieldList({ data }) {
  return (
    <Container>
      <Row>
        {data.map((item) => {
          return (
            <Col key={item.id} lg='4' md="6" sm="12">
              <div className={cx('card-item')}>
                <img src="../field.png" alt="" />
                <div className={cx('info')}>
                  <h2>{item.fieldName}</h2>
                  <p>{item.address}</p>
                  <p>GIÁ: {item.price} VND</p>
                  <button className="col-md-4 col-sm-12">THUÊ</button>
                </div>
              </div>
            </Col>
          );
        })}
      </Row>
    </Container>
  );
}

export default FieldList;
