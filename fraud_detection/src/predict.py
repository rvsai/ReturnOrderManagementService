import pandas as pd
import joblib

def load_model_and_scaler(model_path):
    model, preprocessor = joblib.load(model_path)
    return model, preprocessor

def prepare_input(input_data, preprocessor):
    df_input = pd.DataFrame([input_data])
    input_processed = preprocessor.transform(df_input)
    return input_processed

def predict(input_data, model, preprocessor):
    input_processed = prepare_input(input_data, preprocessor)
    return model.predict(input_processed)

if __name__ == '__main__':
    model, preprocessor = load_model_and_scaler('../data/random_forest_model.pkl')
    # Example input data
    input_data = {'PurchaseAmount': 120.50, 'ReturnCondition': 'Damaged'}
    prediction = predict(input_data, model, preprocessor)
    print("Prediction:", 'Fraud' if prediction[0] else 'Not Fraud')
