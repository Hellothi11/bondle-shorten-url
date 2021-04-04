import React, {useEffect, useState} from 'react';
import PropTypes from 'prop-types';
import {Button, List, Menu, Sidebar, Popup} from 'semantic-ui-react';
import axios from '../../utils/axios';
import styles from './styles.module.css';
import {getUUID} from '../../utils/uuid';

const ListUrls = ({visible, onHide}) => {
  const [urls, setUrls] = useState([]);
  useEffect(() => {
    if (visible) {
      axios.get(`/url/${getUUID()}`).then((response) => {
        setUrls(response.data.payload);
      });
    }
  }, [visible]);

  const deleteURL = (id) => {
    axios.delete(`/url/${id}`).then(() => {
      setUrls((prevState) => prevState.filter((u) => u.id !== id));
    });
  };

  return (
    <Sidebar
      as={Menu}
      animation="overlay"
      onHide={onHide}
      direction="right"
      visible={visible}
      className={styles.list_urls_container}
    >
      <List divided relaxed size="large" className={styles.list_urls}>
        {urls.map(({id, longUrl, shortUrl}) => (
          <List.Item key={id} className={styles.url_item}>
            <List.Content floated="right">
              <Button.Group>
                <Popup
                  content="Visit your shortened URL"
                  trigger={
                    <Button
                      icon="hand point right outline"
                      content="Visit"
                      labelPosition="left"
                      color="orange"
                      onClick={() => {
                        window.open(shortUrl);
                      }}
                    />
                  }
                />
                <Popup
                  content="Copy URL to clipboard"
                  trigger={
                    <Button
                      color="blue"
                      icon="copy"
                      content="Copy"
                      labelPosition="left"
                      onClick={() => {
                        window.navigator.clipboard.writeText(shortUrl);
                      }}
                    />
                  }
                />
              </Button.Group>
              <Popup
                content="Delete URL"
                trigger={
                  <Button
                    className={styles.button_delete}
                    color="red"
                    icon="delete"
                    onClick={() => {
                      deleteURL(id);
                    }}
                  />
                }
              />
            </List.Content>
            <List.Icon name="linkify" size="large" verticalAlign="middle" />
            <List.Content>
              <List.Header>{shortUrl}</List.Header>
              <List.Description>{longUrl}</List.Description>
            </List.Content>
          </List.Item>
        ))}
      </List>
    </Sidebar>
  );
};

ListUrls.propTypes = {
  visible: PropTypes.bool,
  onHide: PropTypes.func,
};

export default ListUrls;
