import { Col, Container, Row } from "react-bootstrap";
import { Link } from "react-router-dom";

import { Tooltip } from "@mui/material";


import classNames from "classnames/bind";
import styles from "./FieldList.module.scss";
const cx = classNames.bind(styles)

function FieldList({ data, action }) {

  return (
    <Container>
      <Row>
        {data.map((item) => {
          return (
            <Col key={item.id} lg='4' md="6" sm="12">
              <div className={cx('card-item')}>
                <div className={cx('thumbnail')}><img src={item.thumbnailUrl} alt="thumbnail" /></div>
                <div className={cx('info')}>
                  <Tooltip title={item.fieldName}><Link to="/fieldinfo"><h2>{item.fieldName}</h2></Link></Tooltip>
                  <p>{item.address}</p>
                  <p>GIÁ: {item.price} VND</p>
                  <button className="col-md-4 col-sm-12" onClick={e => action(item)}>THUÊ</button>
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
