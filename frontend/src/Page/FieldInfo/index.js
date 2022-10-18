import { Col, Container, Row } from "react-bootstrap";

import classNames from "classnames/bind";
import styles from "./FieldInfo.module.scss";
const cx = classNames.bind(styles)


function FieldInfoPage() {
    return (
        <Container className={cx('container')}>
           <div className={cx("content")}>
                <Row className={cx('row')}>
                        <Col md='4'>
                            <div >
                                <img src="../field.png" alt="" />
                            </div>
                        </Col>
                        <Col md='8'>
                            <div class="FieldList_info__EfC73">
                                <a href="/fieldinfo"><h2>Sân bóng Trí Hải - Khu Thể Thao An Phú</h2></a>
                                <p>204A, Mai Chí Thọ, Khu Phố 3, P. An Phú, Q2, TP.HCM</p>
                                <p>GIÁ: 200000 VND</p><button class="col-md-4 col-sm-12">THUÊ</button>
                            </div>
                        </Col>
                </Row>
           </div>
        </Container>
    );
}

export default FieldInfoPage;