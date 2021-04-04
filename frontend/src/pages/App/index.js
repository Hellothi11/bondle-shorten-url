import React, {useState} from 'react';
import 'semantic-ui-css/semantic.min.css';
import styles from './styles.module.css';
import logo from '../../images/bondle-logo-color.png';
import {Icon, Image, Menu, Sidebar} from 'semantic-ui-react';
import ListUrls from './ListUrls';
import FormCreateUrl from './FormCreateUrl';

export default function App() {
  const [visible, setVisible] = useState(false);
  const openMyURLs = () => {
    setVisible(true);
  };

  return (
    <div className={styles.body}>
      <Menu fixed="top" inverted className={styles.app_menu}>
        <Menu.Item as="a" header>
          <Image size="tiny" src={logo} className={styles.header_logo} />
          URL Shortener
        </Menu.Item>
        <Menu.Item position="right" as="a" onClick={openMyURLs}>
          <Icon name="list" />
          <text>My URLs</text>
        </Menu.Item>
      </Menu>
      <div className={styles.content}>
        <Sidebar.Pushable>
          <Sidebar.Pusher>
            <FormCreateUrl openMyURLs={openMyURLs} />
          </Sidebar.Pusher>
          <ListUrls
            visible={visible}
            onHide={() => {
              setVisible(false);
            }}
          />
        </Sidebar.Pushable>
      </div>
    </div>
  );
}
