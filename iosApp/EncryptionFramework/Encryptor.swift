import Foundation
import CryptoKit

@objc(Encryptor)
public class Encryptor: NSObject {
  private let key: SymmetricKey
  
  @objc public override init() {
    // Example: 32 bytes (256 bits) key filled with 0x42, replace with secure key management!
    let keyData = Data(repeating: 0x42, count: 32)
    self.key = SymmetricKey(data: keyData)
    super.init()
  }
  
  @objc public func encrypt(_ data: NSData) -> NSData {
    let input = Data(referencing: data)
    let nonce = AES.GCM.Nonce()
    let sealedBox = try! AES.GCM.seal(input, using: key, nonce: nonce)
    // Combine nonce + ciphertext + tag
    var encrypted = Data()
    encrypted.append(contentsOf: nonce)
    encrypted.append(sealedBox.ciphertext)
    encrypted.append(sealedBox.tag)
    return encrypted as NSData
  }
  
  @objc public func decrypt(_ data: NSData) -> NSData? {
    let input = Data(referencing: data)
    guard input.count >= 12 + 16 else {
      return nil
    } // nonce(12) + tag(16)
    let nonce = try! AES.GCM.Nonce(data: input.prefix(12))
    let tag = input.suffix(16)
    let ciphertext = input.dropFirst(12).dropLast(16)
    let sealedBox = try? AES.GCM.SealedBox(nonce: nonce, ciphertext: ciphertext, tag: tag)
    guard let box = sealedBox,
          let decrypted = try? AES.GCM.open(box, using: key)
    else {
      return nil
    }
    return decrypted as NSData
  }
}
