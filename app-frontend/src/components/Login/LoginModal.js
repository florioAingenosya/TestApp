import React, { Component } from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Label, Alert, Row, Col } from 'reactstrap';
import { AvForm, AvField, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { GOOGLE_AUTH_URL, FACEBOOK_AUTH_URL } from '../../constants';
import fbLogo from '../../images/fb-logo.png';
import googleLogo from '../../images/google-logo.png';
import styles from './LoginModal.scss';
import classNames from 'classnames/bind';
const cx = classNames.bind(styles);

class LoginModal extends Component {

  constructor(props) {
    super(props);

    this.state = {
      loginError: false
    };
  }

  handleSubmit = (event, errors, { username, password, rememberMe }) => {
    const { handleLogin } = this.props;
    handleLogin(username, password, rememberMe);
  };

  render() {
    const { loginError, handleClose, showModal } = this.props;

    return (
      <Modal isOpen={showModal} toggle={handleClose} >
        <AvForm onSubmit={this.handleSubmit}>
          <ModalHeader id="login-title" toggle={handleClose}>
            Connexion
          </ModalHeader>
          <ModalBody>
            <Row>
              <Col md="12">
                {loginError ? (
                  <Alert color="danger">
                    <strong>Échec de la connexion!</strong> Veuillez vérifier vos informations d'identification et réessayer.
                  </Alert>
                ) : null}
              </Col>
              <Col md="12">
                <Alert color="warning">
                <div >
                  Login : admin@mail.com <br></br>
                  Mdp : admin
                </div>
                </Alert>
                <AvField
                  name="username"
                  label="Username"
                  placeholder="Nom d'utilisateur"
                  required
                  errorMessage="Nom d'utilisateur obligatoire!"
                  autoFocus
                />
                <AvField
                  name="password"
                  type="password"
                  label="Password"
                  placeholder="Mot de passe"
                  required
                  errorMessage="Mot de passe obligatoire!"
                />
                <AvGroup check inline>
                  <Label className="form-check-label">
                    <AvInput type="checkbox" name="rememberMe" /> Se souvenir de moi
                  </Label>
                </AvGroup>
              </Col>
            </Row>
            <div className="mt-1">&nbsp;</div>
            <div className="login-container">
              <SocialLogin />
            </div>
          </ModalBody>
          <ModalFooter>
            <Button color="secondary" onClick={handleClose} tabIndex="1">
              Annuler
            </Button>{' '}
            <Button color="primary" type="submit">
              Connexion
            </Button>
          </ModalFooter>
        </AvForm>
      </Modal>
    );
  }
}

class SocialLogin extends Component {
  render() {
    return (
      <div className="social-login">
        <Button block color="link" className={cx("google")} href={GOOGLE_AUTH_URL}>
          <img src={googleLogo} alt="Google" /> Connexion ou Inscription avec Google
        </Button>
        
      </div>
    );
  }
}

export default LoginModal;
