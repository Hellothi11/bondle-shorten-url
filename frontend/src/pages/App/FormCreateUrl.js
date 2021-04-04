import React, {useState, useRef} from 'react';
import {
  Container,
  Card,
  Form,
  Button,
  Input,
  Icon,
  Segment,
  Loader,
} from 'semantic-ui-react';
import PropTypes from 'prop-types';
import classnames from 'classnames';
import styles from './styles.module.css';
import useShortenURL from '../../hooks/useShortenURL';

const FormCreateUrl = ({openMyURLs}) => {
  const [loading, setLoading] = useState(false);
  const [url, setUrl] = useState(null);
  const shortenUrl = () => {
    setLoading(true);
    setTimeout(() => {
      setUrl({
        id: 1,
        longUrl: 'https://google.com.vn',
        shortUrl: 'https://link.bondle.com/82y1h',
      });
      setLoading(false);
    }, 5000);
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
