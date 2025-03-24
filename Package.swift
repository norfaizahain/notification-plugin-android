// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "NotificationPluginAndroid",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "NotificationPluginAndroid",
            targets: ["NotificationPluginPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "NotificationPluginPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/NotificationPluginPlugin"),
        .testTarget(
            name: "NotificationPluginPluginTests",
            dependencies: ["NotificationPluginPlugin"],
            path: "ios/Tests/NotificationPluginPluginTests")
    ]
)
