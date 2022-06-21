import { Col, Container, Row } from "react-bootstrap";

import styles from "./footer.module.scss";
import classNames from "classnames/bind";
const cx = classNames.bind(styles)

function Footer() {
  return (
    <Container fluid>
      <div className={cx('footer')}>
        <Row className={cx('descripsion')}>
          <Col sm='12' md='4'>
            <h1>SPORT EXPLORER</h1>
          </Col>
          <Col sm='12' md='8'>
            <p>Sologan</p>
          </Col>
        </Row>
        <Row className={cx('contact')}>
          <p>
            Email: <a href="mailto:someone@example.com">someone@example.com</a>
          </p>
          <p>
            Phone: <a href="tel:0123456789">0123456789</a>
          </p>
        </Row>
      </div>
    </Container>
  );
}

export default Footer;
