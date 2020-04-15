//
// Created by Yevhenii Kanivets on 11/01/2020.
// Copyright (c) 2020 xorum.io. All rights reserved.
//

import Foundation
import HTMLString

extension String {
    
    mutating func deleteHtmlTags() {
        self = self.replacingOccurrences(of: "<[^>]+>", with: "", options: .regularExpression, range: nil)
    }

    func beautify() -> String {
        var newString = self
        newString.deleteHtmlTags()
        newString.removeHTMLEntities()
        newString = newString.replacingOccurrences(of: "$$$", with: "")

        return newString
    }

    var localized: String {
        let string = NSLocalizedString(self, comment: "")

        if
            string == self, //the translation was not found
            let baseLanguagePath = Bundle.main.path(forResource: "Base", ofType: "lproj"),
            let baseLangBundle = Bundle(path: baseLanguagePath) {

            return NSLocalizedString(self, bundle: baseLangBundle, comment: "")
        } else {
            return string
        }
    }

    func dateStringToDate() -> Date {
        let formatter = DateFormatter().apply {
            $0.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        }
        return formatter.date(from: self)!
    }
    
    var attributed: NSMutableAttributedString { return NSMutableAttributedString(string: self) }
    
    func colorString(color: UIColor) -> NSAttributedString {
        return self.attributed.apply {
            $0.addAttribute(NSAttributedString.Key.foregroundColor, value: color, range: NSRange(location: 0, length: self.count))
        }
    }
}

func buildShareText(_ title: String, _ link: String) -> String {
    return """
           \(title) - \(link)

           Shared through Codeforces Watcher. Find it on App Store.
           """
}
