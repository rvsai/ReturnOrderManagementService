import joblib
from preprocessing import load_data, preprocess_data, split_data
from sklearn.ensemble import RandomForestClassifier

def train_and_save_model(data_path, model_path):
    data = load_data(data_path)
    X, y, preprocessor = preprocess_data(data)
    X_train, X_test, y_train, y_test = split_data(X, y)

    # Initialize and train the RandomForest model
    model = RandomForestClassifier(n_estimators=100, random_state=42)
    model.fit(X_train, y_train)

    # Save the model and preprocessor together
    joblib.dump((model, preprocessor), model_path)

if __name__ == '__main__':
    train_and_save_model('../data/Fraud_return_Orders.csv', '../data/random_forest_model.pkl')
