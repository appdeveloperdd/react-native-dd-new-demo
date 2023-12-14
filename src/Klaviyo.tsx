import { NativeModules, Platform } from 'react-native';

interface KlaviyoModule {
  initializeKlaviyo(publicKey: string): void;
  setProfile(email: string): void;
  resetProfile(): void;
  createEvent(eventName: string, properties: object): void;
  setPushToken(pushToken: string): void;
}

const KlaviyoBridge: KlaviyoModule =
  Platform.OS === 'android'
    ? NativeModules.DdNewDemoModule
    : NativeModules.DdNewDemo;

class Klaviyo {
  static initializeKlaviyo(publicKey: string): void {
    KlaviyoBridge.initializeKlaviyo(publicKey);
  }

  static setProfile(email: string): void {
    KlaviyoBridge.setProfile(email);
  }

  static resetProfile(): void {
    if (Platform.OS === 'android') {
      KlaviyoBridge.resetProfile();
    }
  }

  static createEvent(eventName: string, properties: object): void {
    KlaviyoBridge.createEvent(eventName, properties);
  }

  static setPushToken(pushToken: string): void {
    KlaviyoBridge.setPushToken(pushToken);
  }
}

export default Klaviyo;
