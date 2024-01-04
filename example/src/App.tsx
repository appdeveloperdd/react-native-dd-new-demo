import * as React from 'react';

import { Button, SafeAreaView, ScrollView } from 'react-native';
import Klaviyo from 'react-native-dd-new-demo';

const App = () => {
  React.useEffect(() => {
    onPress();
  }, []);

  const onPress = () => {
    Klaviyo.initializeKlaviyo('SHmAn7');
  };
  const onPressSetProfile = () => {
    Klaviyo.setProfile('programmer90.dynamicdreamz@gmail.com');
  };
  const onPressResetProfile = () => {
    Klaviyo.resetProfile();
  };
  const onPressCreateEvent = () => {
    Klaviyo.createEvent('Add to Cart2323233', {
      'currency': 'INR',
      'Total Price': 6680,
      'Product quantity': '2',
      'Product Price': ['2890', '3790'],
      'Product ID': ['6546413158563', '7140660805795'],
      'Product Title': [
        'Grey Houndstooth Shirt',
        '2-Ply Iceberg White Poplin Shirt',
      ],
      'Vendor name': ['Bombay Shirt Company', 'Bombay Shirt Company'],
      'Customer first name': 'Dilip',
      'Customer last name': 'Kaklotar',
      'Product Type': ['Shirt', 'Shirt'],
      'Product URL': [
        '/products/bsc-grey-houndstooth-shirt',
        '/products/bsc-2-ply-iceberg-white-poplin-shirt',
      ],
      'Product image URL': [
        'https://cdn.shopify.com/s/files/1/0536/3594/0515/products/e4f9c101-2020-20ee-5199-290afa98c9fe_BB016F_250x.jpg?v=1700908902',
        'https://cdn.shopify.com/s/files/1/0536/3594/0515/products/7dbb26af-a8d3-3a29-9f73-193c114f3b69_PS0121A_250x.jpg?v=1701088081',
      ],
      'Quantity': ['1', '1'],
      'Variant ID': ['39295208095907', '41045393014947'],
      'Checkout URL':
        'https://bombayshirts-india-prod.myshopify.com/53635940515/checkouts/b43b1d33f6059e2bb1382aa012c7fa41?key=cbf84b740909e0d7e5493517987a81f3',
      'Source': 'Shopify IOS App',
    });
  };

  const onPressSetPushToken = () => {
    Klaviyo.setPushToken('0123456789abcdef0123456789abcdef');
  };

  return (
    <SafeAreaView>
      <ScrollView contentInsetAdjustmentBehavior="automatic">
        {/* <Button
          title="Click to invoke your native module!"
          color="#841584"
          style={{marginVertical: 20}}
          onPress={onPress}
        /> */}
        <Button
          title="Set Profile"
          color="#841584"
          onPress={onPressSetProfile}
        />

        <Button
          title="Reset Profile"
          color="#841584"
          onPress={onPressResetProfile}
        />

        <Button
          title="Create Event"
          color="#841584"
          onPress={onPressCreateEvent}
        />

        <Button
          title="Set Push Token"
          color="#841584"
          onPress={onPressSetPushToken}
        />
      </ScrollView>
    </SafeAreaView>
  );
};

export default App;
