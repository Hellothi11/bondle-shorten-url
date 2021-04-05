import React, {useState, useRef} from 'react';
import {
  Container,
  Card,
  Button,
  Input,
  Icon,
  Loader,
  Message,
} from 'semantic-ui-react';
import PropTypes from 'prop-types';
import styles from './styles.module.css';
import {getUUID} from '../../utils/uuid';
import axios from '../../utils/axios';

const FormCreateUrl = ({openMyURLs}) => {
  const [loading, setLoading] = useState(false);
  const [url, setUrl] = useState(null);
  const [errors, setErrors] = useState(null);
  const shortenUrl = (longUrl) => {
    let url = longUrl;
    if (!longUrl.startsWith('http')) {
      url = `http://${longUrl}`;
    }
    setLoading(true);
    axios
      .post('/url', {
        uuid: getUUID(),
        url,
      })
      .then((response) => {
        setErrors(null);
        setUrl(response.data.payload);
        setLoading(false);
      })
      .catch((error) => {
        if (error.response && error.response.data.errors) {
          setErrors(error.response.data.errors);
        } else {
          setErrors([{message: 'Network has a problem'}]);
        }
        setLoading(false);
      });
  };
  const inputUrl = useRef();

  return (
    <Container className={styles.home_container}>
      {url ? (
        <Card className={styles.card}>
          <Card.Content>
            <Card.Header className={styles.card_header}>
              <Icon name="linkify" />
              Your long URL
            </Card.Header>
            <div className={styles.label_url}>{url.longUrl}</div>
            <Card.Header className={styles.card_header}>
              <Icon name="magic" />
              Your shortened URL
            </Card.Header>
            <div className={styles.label_url}>{url.shortUrl}</div>
            <Card.Content extra>
              <div className={styles.extra_button_wrapper}>
                <Button
                  className={styles.extra_button}
                  icon="angle right"
                  content="Shorten another"
                  onClick={() => {
                    setLoading(false);
                    setUrl(null);
                  }}
                />
                <Button
                  className={styles.extra_button}
                  basic
                  color="orange"
                  icon="list"
                  content="My URLs"
                  onClick={() => {
                    openMyURLs();
                  }}
                />
              </div>
            </Card.Content>
          </Card.Content>
        </Card>
      ) : (
        <Card className={styles.card}>
          <Card.Content>
            <Card.Header className={styles.card_header}>
              Enter the long URL to shorten
            </Card.Header>
            <Input
              icon
              type="text"
              iconPosition="left"
              className={styles.input_url}
            >
              <Icon name="arrow alternate circle right" />
              <input type="text" ref={inputUrl} />
            </Input>
            {errors && errors.length > 0 && (
              <Message error list={errors.map((e) => e.message)} />
            )}
            <Button
              color="black"
              fluid
              size="large"
              disabled={loading}
              onClick={() => {
                if (inputUrl.current && inputUrl.current.value) {
                  shortenUrl(inputUrl.current.value);
                }
              }}
            >
              {loading ? <Loader active inline="centered" /> : 'Shorten URL'}
            </Button>
          </Card.Content>
        </Card>
      )}
    </Container>
  );
};

FormCreateUrl.propTypes = {
  openMyURLs: PropTypes.func.isRequired,
};

export default FormCreateUrl;
